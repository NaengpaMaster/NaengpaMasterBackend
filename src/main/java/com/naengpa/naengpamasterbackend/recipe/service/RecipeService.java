package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.global.exception.RecipeNotFoundException;
import com.naengpa.naengpamasterbackend.member.entity.FoodCategory;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.repository.FoodCategoryRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeDetailResponse;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeFoodCategory;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeRequiredProduct;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeStep;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeFavoriteRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeFoodCategoryRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRequiredProductRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeStepRepository;
import com.naengpa.naengpamasterbackend.fridge.repository.FridgeItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeStepRepository recipeStepRepository;
    private final RecipeRequiredProductRepository recipeRequiredProductRepository;
    private final RecipeFoodCategoryRepository recipeFoodCategoryRepository;
    private final RecipeFavoriteRepository recipeFavoriteRepository;
    private final ProductRepository productRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final FridgeItemRepository fridgeItemRepository;
    private final MemberRepository memberRepository;

    public RecipeDetailResponse getRecipeDetail(Long recipeId, String email) {
        Recipe recipe = recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(RecipeNotFoundException::new);

        Member member = resolveMember(email);
        Long memberId = member == null ? null : member.getId();
        boolean canManage = member != null
                && (recipe.isOwnedBy(memberId) || member.getRole() == MemberRole.ADMIN);

        long likeCount = recipeFavoriteRepository.countByRecipeId(recipeId);
        Boolean liked = memberId == null
                ? null
                : recipeFavoriteRepository.existsByRecipeIdAndMemberId(recipeId, memberId);

        Long foodCategoryId = recipeFoodCategoryRepository.findByRecipeId(recipeId)
                .map(RecipeFoodCategory::getFoodCategoryId)
                .orElse(null);
        String foodCategoryName = foodCategoryId == null
                ? null
                : foodCategoryRepository.findById(foodCategoryId)
                        .map(FoodCategory::getName)
                        .orElse(null);

        List<RecipeRequiredProduct> requiredProducts =
                recipeRequiredProductRepository.findByRecipeIdOrderByRecipeRequiredProductIdAsc(recipeId);
        List<Long> productIds = requiredProducts.stream()
                .map(RecipeRequiredProduct::getProductId)
                .toList();

        Map<Long, String> productNames = productIds.isEmpty()
                ? Map.of()
                : productRepository.findByProductIdInAndIsActiveTrue(productIds).stream()
                        .collect(Collectors.toMap(Product::getProductId, Product::getName));

        Set<Long> ownedProductIds = (memberId == null || productIds.isEmpty())
                ? Set.of()
                : fridgeItemRepository.findByMemberIdAndProductIdInAndIsDeletedFalse(memberId, productIds).stream()
                        .map(item -> item.getProductId())
                        .collect(Collectors.toSet());

        List<RecipeDetailResponse.IngredientItem> ingredients = requiredProducts.stream()
                .map(rp -> new RecipeDetailResponse.IngredientItem(
                        rp.getProductId(),
                        productNames.getOrDefault(rp.getProductId(), null),
                        ownedProductIds.contains(rp.getProductId())
                ))
                .toList();

        List<String> missingIngredients = ingredients.stream()
                .filter(ingredient -> !ingredient.owned())
                .map(RecipeDetailResponse.IngredientItem::ingredientName)
                .toList();

        List<RecipeDetailResponse.StepItem> steps =
                recipeStepRepository.findByRecipeIdAndDeletedFalseOrderByStepNoAsc(recipeId).stream()
                        .map(step -> new RecipeDetailResponse.StepItem(step.getStepNo(), step.getContent()))
                        .toList();

        return new RecipeDetailResponse(
                recipe.getRecipeId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getCategory().getName(),
                foodCategoryId,
                foodCategoryName,
                recipe.getCookingTime(),
                recipe.getDifficulty().name(),
                likeCount,
                liked,
                canManage,
                ingredients,
                missingIngredients,
                steps
        );
    }

    private Member resolveMember(String email) {
        if (!StringUtils.hasText(email)) {
            return null;
        }
        return memberRepository.findByEmail(email).orElse(null);
    }
}
