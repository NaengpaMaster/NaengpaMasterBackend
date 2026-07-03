package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import com.naengpa.naengpamasterbackend.fridge.repository.FridgeItemRepository;
import com.naengpa.naengpamasterbackend.global.response.PageResponse;
import com.naengpa.naengpamasterbackend.member.entity.FoodCategory;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberExcludedProduct;
import com.naengpa.naengpamasterbackend.member.entity.MemberFavoriteFood;
import com.naengpa.naengpamasterbackend.member.repository.MemberExcludedProductRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberFavoriteFoodRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeRecommendationResponse;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeFoodCategory;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeRequiredProduct;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeFavoriteRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeFoodCategoryRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeLikeCountProjection;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRequiredProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeRecommendationService {

    /** 유통기한 임박 기준(일). */
    private static final int EXPIRY_SOON_DAYS = 3;
    /** 우선 추천 보유 재료 비율 기준(%). */
    private static final int HIGH_MATCH_RATE = 80;

    private final RecipeRepository recipeRepository;
    private final RecipeRequiredProductRepository recipeRequiredProductRepository;
    private final RecipeFoodCategoryRepository recipeFoodCategoryRepository;
    private final RecipeFavoriteRepository recipeFavoriteRepository;
    private final ProductRepository productRepository;
    private final FridgeItemRepository fridgeItemRepository;
    private final MemberRepository memberRepository;
    private final MemberFavoriteFoodRepository memberFavoriteFoodRepository;
    private final MemberExcludedProductRepository memberExcludedProductRepository;

    public PageResponse<RecipeRecommendationResponse> recommend(String email, String keyword,
                                                                boolean favoriteOnly, boolean match80Only,
                                                                int page, int size) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 정보가 유효하지 않습니다."));

        MemberContext context = loadMemberContext(member);

        List<Recipe> candidates;

        if (StringUtils.hasText(keyword)) {
            candidates = recipeRepository.searchRecommendationCandidates(keyword);
        } else {
            candidates = recipeRepository.findRecommendationCandidates();
        }

        Set<Long> favoriteRecipeIds = Set.copyOf(recipeFavoriteRepository.findRecipeIdsByMemberId(member.getId()));

        if (favoriteOnly) {
            candidates = candidates.stream()
                    .filter(recipe -> favoriteRecipeIds.contains(recipe.getRecipeId()))
                    .toList();
        }

        Map<Long, List<RecipeRequiredProduct>> requiredByRecipe = loadRequiredProducts(candidates);
        Map<Long, String> productNames = loadProductNames(requiredByRecipe.values());
        Map<Long, Long> likeCounts = loadLikeCounts(candidates);
        Map<Long, Long> foodCategoryIds = loadFoodCategoryIds(candidates);

        List<RecipeRecommendationResponse> scored = candidates.stream()
                .map(recipe -> score(recipe, requiredByRecipe.getOrDefault(recipe.getRecipeId(), List.of()),
                        productNames, context, likeCounts.getOrDefault(recipe.getRecipeId(), 0L),
                        favoriteRecipeIds.contains(recipe.getRecipeId()),
                        foodCategoryIds.get(recipe.getRecipeId())))
                .filter(java.util.Objects::nonNull)
                .filter(scoredRecipe -> !match80Only || scoredRecipe.response().matchRate() >= HIGH_MATCH_RATE)
                .sorted(Comparator
                        .comparingInt(ScoredRecipe::score).reversed()
                        .thenComparing(scoredRecipe -> scoredRecipe.recipe().getCreatedAt(),
                                Comparator.reverseOrder()))
                .map(ScoredRecipe::response)
                .toList();

        List<RecipeRecommendationResponse> content = paginate(scored, page, size);
        return PageResponse.of(content, page, size, scored.size());
    }

    private MemberContext loadMemberContext(Member member) {
        List<FridgeItem> fridgeItems = fridgeItemRepository.findByMemberIdAndIsDeletedFalse(member.getId());
        Set<Long> ownedProductIds = fridgeItems.stream()
                .map(FridgeItem::getProductId)
                .collect(Collectors.toSet());

        LocalDate threshold = LocalDate.now().plusDays(EXPIRY_SOON_DAYS);
        Set<Long> expiringProductIds = fridgeItems.stream()
                .filter(item -> item.getExpiryDate() != null && !item.getExpiryDate().isAfter(threshold))
                .map(FridgeItem::getProductId)
                .collect(Collectors.toSet());

        Set<Long> excludedProductIds = memberExcludedProductRepository.findAllByMemberWithProduct(member).stream()
                .map(MemberExcludedProduct::getProduct)
                .map(Product::getProductId)
                .collect(Collectors.toSet());

        Set<Long> favoriteFoodCategoryIds = memberFavoriteFoodRepository.findAllByMemberOrderByIdAsc(member).stream()
                .map(MemberFavoriteFood::getFoodCategory)
                .map(FoodCategory::getId)
                .collect(Collectors.toSet());

        return new MemberContext(ownedProductIds, expiringProductIds, excludedProductIds, favoriteFoodCategoryIds);
    }

    private Map<Long, List<RecipeRequiredProduct>> loadRequiredProducts(List<Recipe> candidates) {
        if (candidates.isEmpty()) {
            return Map.of();
        }
        List<Long> recipeIds = candidates.stream().map(Recipe::getRecipeId).toList();
        return recipeRequiredProductRepository.findByRecipeIdIn(recipeIds).stream()
                .collect(Collectors.groupingBy(RecipeRequiredProduct::getRecipeId));
    }

    private Map<Long, String> loadProductNames(java.util.Collection<List<RecipeRequiredProduct>> requiredGroups) {
        List<Long> productIds = requiredGroups.stream()
                .flatMap(List::stream)
                .map(RecipeRequiredProduct::getProductId)
                .distinct()
                .toList();
        if (productIds.isEmpty()) {
            return Map.of();
        }
        return productRepository.findByProductIdInAndIsActiveTrue(productIds).stream()
                .collect(Collectors.toMap(Product::getProductId, Product::getName));
    }

    private Map<Long, Long> loadLikeCounts(List<Recipe> candidates) {
        if (candidates.isEmpty()) {
            return Map.of();
        }
        List<Long> recipeIds = candidates.stream().map(Recipe::getRecipeId).toList();
        return recipeFavoriteRepository.countByRecipeIdIn(recipeIds).stream()
                .collect(Collectors.toMap(RecipeLikeCountProjection::getRecipeId, RecipeLikeCountProjection::getLikeCount));
    }

    private Map<Long, Long> loadFoodCategoryIds(List<Recipe> candidates) {
        if (candidates.isEmpty()) {
            return Map.of();
        }
        List<Long> recipeIds = candidates.stream().map(Recipe::getRecipeId).toList();
        return recipeFoodCategoryRepository.findByRecipeIdIn(recipeIds).stream()
                .collect(Collectors.toMap(RecipeFoodCategory::getRecipeId, RecipeFoodCategory::getFoodCategoryId));
    }

    private ScoredRecipe score(Recipe recipe, List<RecipeRequiredProduct> required,
                               Map<Long, String> productNames, MemberContext context, long likeCount, boolean liked,
                               Long foodCategoryId) {
        List<Long> requiredProductIds = required.stream()
                .map(RecipeRequiredProduct::getProductId)
                .toList();

        // 못 먹는 재료가 포함된 레시피는 추천 대상에서 제외
        if (requiredProductIds.stream().anyMatch(context.excludedProductIds()::contains)) {
            return null;
        }

        int total = requiredProductIds.size();
        long ownedCount = requiredProductIds.stream().filter(context.ownedProductIds()::contains).count();
        int matchRate = total == 0 ? 0 : (int) Math.round(ownedCount * 100.0 / total);

        List<String> missingIngredients = required.stream()
                .filter(rp -> !context.ownedProductIds().contains(rp.getProductId()))
                .map(rp -> productNames.getOrDefault(rp.getProductId(), null))
                .filter(java.util.Objects::nonNull)
                .toList();

        boolean usesExpiring = requiredProductIds.stream()
                .anyMatch(context.expiringProductIds()::contains);
        boolean matchesPreference = foodCategoryId != null
                && context.favoriteFoodCategoryIds().contains(foodCategoryId);

        List<String> reasons = new ArrayList<>();
        reasons.add("보유 재료 " + matchRate + "%");
        if (usesExpiring) {
            reasons.add("유통기한 임박 재료 활용");
        }
        if (matchesPreference) {
            reasons.add("선호 음식과 일치");
        }

        int score = matchRate
                + (matchRate >= HIGH_MATCH_RATE ? 100 : 0)
                + (usesExpiring ? 50 : 0)
                + (matchesPreference ? 30 : 0);

        RecipeRecommendationResponse response = new RecipeRecommendationResponse(
                recipe.getRecipeId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getCategory().getName(),
                recipe.getDifficulty().getLabel(),
                recipe.getCookingTime(),
                matchRate,
                likeCount,
                liked,
                missingIngredients,
                reasons,
                List.of()
        );
        return new ScoredRecipe(recipe, score, response);
    }

    private List<RecipeRecommendationResponse> paginate(List<RecipeRecommendationResponse> scored,
                                                        int page, int size) {
        int fromIndex = page * size;
        if (fromIndex >= scored.size()) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + size, scored.size());
        return scored.subList(fromIndex, toIndex);
    }

    private record MemberContext(
            Set<Long> ownedProductIds,
            Set<Long> expiringProductIds,
            Set<Long> excludedProductIds,
            Set<Long> favoriteFoodCategoryIds
    ) {
    }

    private record ScoredRecipe(
            Recipe recipe,
            int score,
            RecipeRecommendationResponse response
    ) {
    }
}
