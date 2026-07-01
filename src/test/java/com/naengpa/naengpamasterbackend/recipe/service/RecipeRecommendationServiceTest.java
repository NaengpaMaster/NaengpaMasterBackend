package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import com.naengpa.naengpamasterbackend.fridge.repository.FridgeItemRepository;
import com.naengpa.naengpamasterbackend.global.response.PageResponse;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberExcludedProductRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberFavoriteFoodRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeRecommendationResponse;
import com.naengpa.naengpamasterbackend.recipe.entity.Difficulty;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeCategory;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeRequiredProduct;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeFavoriteRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRequiredProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RecipeRecommendationServiceTest {

    private static final String EMAIL = "user@example.com";

    @Mock RecipeRepository recipeRepository;
    @Mock RecipeRequiredProductRepository recipeRequiredProductRepository;
    @Mock RecipeFavoriteRepository recipeFavoriteRepository;
    @Mock ProductRepository productRepository;
    @Mock FridgeItemRepository fridgeItemRepository;
    @Mock MemberRepository memberRepository;
    @Mock MemberFavoriteFoodRepository memberFavoriteFoodRepository;
    @Mock MemberExcludedProductRepository memberExcludedProductRepository;

    @InjectMocks
    RecipeRecommendationService recipeRecommendationService;

    private Member givenMember(Long id) {
        Member member = Mockito.mock(Member.class);
        given(member.getId()).willReturn(id);
        given(memberRepository.findByEmail(EMAIL)).willReturn(Optional.of(member));
        given(memberFavoriteFoodRepository.findAllByMemberOrderByIdAsc(any())).willReturn(List.of());
        given(memberExcludedProductRepository.findAllByMemberWithProduct(any())).willReturn(List.of());
        return member;
    }

    private Recipe recipe(Long id, String name, String categoryName, LocalDateTime createdAt) {
        RecipeCategory category = Mockito.mock(RecipeCategory.class);
        given(category.getName()).willReturn(categoryName);
        Recipe recipe = Mockito.mock(Recipe.class);
        given(recipe.getRecipeId()).willReturn(id);
        given(recipe.getName()).willReturn(name);
        given(recipe.getDescription()).willReturn(name + " 설명");
        given(recipe.getCategory()).willReturn(category);
        given(recipe.getDifficulty()).willReturn(Difficulty.EASY);
        given(recipe.getCookingTime()).willReturn(15);
        given(recipe.getCreatedAt()).willReturn(createdAt);
        return recipe;
    }

    private RecipeRequiredProduct required(Long recipeId, Long productId) {
        RecipeRequiredProduct rrp = Mockito.mock(RecipeRequiredProduct.class);
        given(rrp.getRecipeId()).willReturn(recipeId);
        given(rrp.getProductId()).willReturn(productId);
        return rrp;
    }

    private FridgeItem fridgeItem(Long productId, LocalDate expiryDate) {
        FridgeItem item = Mockito.mock(FridgeItem.class);
        given(item.getProductId()).willReturn(productId);
        given(item.getExpiryDate()).willReturn(expiryDate);
        return item;
    }

    private Product product(Long id, String name) {
        Product product = Mockito.mock(Product.class);
        given(product.getProductId()).willReturn(id);
        given(product.getName()).willReturn(name);
        return product;
    }

    @Test
    @DisplayName("보유 재료 비율과 부족 재료, 추천 사유를 계산해 추천 목록을 반환한다")
    void recommend_computesMatchRateAndReasons() {
        givenMember(1L);
        // 레시피: 필수재료 productId 10, 20, 30 / 보유: 10, 20 -> matchRate 67%, 부족: 대파(30)
        Recipe recipe = recipe(15L, "김치볶음밥", "한식", LocalDateTime.now());
        List<RecipeRequiredProduct> required =
                List.of(required(15L, 10L), required(15L, 20L), required(15L, 30L));
        List<FridgeItem> fridge = List.of(fridgeItem(10L, null), fridgeItem(20L, null));
        List<Product> products = List.of(product(10L, "김치"), product(20L, "밥"), product(30L, "대파"));
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of(recipe));
        given(recipeRequiredProductRepository.findByRecipeIdIn(List.of(15L))).willReturn(required);
        given(fridgeItemRepository.findByMemberIdAndIsDeletedFalse(1L)).willReturn(fridge);
        given(productRepository.findByProductIdInAndIsActiveTrue(anyList())).willReturn(products);

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, false, false, 0, 10);

        assertThat(result.totalElements()).isEqualTo(1);
        assertThat(result.last()).isTrue();
        RecipeRecommendationResponse item = result.content().get(0);
        assertThat(item.recipeId()).isEqualTo(15L);
        assertThat(item.matchRate()).isEqualTo(67);
        assertThat(item.difficulty()).isEqualTo("쉬움");
        assertThat(item.missingIngredients()).containsExactly("대파");
        assertThat(item.recommendReasons()).contains("보유 재료 67%");
    }

    @Test
    @DisplayName("못 먹는 재료가 포함된 레시피는 추천에서 제외한다")
    void recommend_excludesRecipesWithAvoidProduct() {
        Member member = givenMember(1L);
        com.naengpa.naengpamasterbackend.member.entity.MemberExcludedProduct excluded =
                Mockito.mock(com.naengpa.naengpamasterbackend.member.entity.MemberExcludedProduct.class);
        Product avoid = product(30L, "대파");
        given(excluded.getProduct()).willReturn(avoid);
        given(memberExcludedProductRepository.findAllByMemberWithProduct(member)).willReturn(List.of(excluded));

        Recipe recipe = recipe(15L, "김치볶음밥", "한식", LocalDateTime.now());
        List<RecipeRequiredProduct> required = List.of(required(15L, 10L), required(15L, 30L));
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of(recipe));
        given(recipeRequiredProductRepository.findByRecipeIdIn(List.of(15L))).willReturn(required);
        given(fridgeItemRepository.findByMemberIdAndIsDeletedFalse(1L)).willReturn(List.of());

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, false, false, 0, 10);

        assertThat(result.totalElements()).isZero();
        assertThat(result.content()).isEmpty();
    }

    @Test
    @DisplayName("추천 점수가 높은 레시피가 먼저 정렬된다")
    void recommend_sortsByScoreDescending() {
        givenMember(1L);
        // 레시피 A: 모든 재료 보유(100%), 레시피 B: 절반 보유(50%)
        Recipe high = recipe(1L, "재료많은요리", "한식", LocalDateTime.now().minusDays(1));
        Recipe low = recipe(2L, "재료부족요리", "한식", LocalDateTime.now());
        List<RecipeRequiredProduct> required = List.of(
                required(1L, 10L), required(1L, 20L),
                required(2L, 10L), required(2L, 30L));
        List<FridgeItem> fridge = List.of(fridgeItem(10L, null), fridgeItem(20L, null));
        List<Product> products = List.of(product(10L, "김치"), product(20L, "밥"), product(30L, "대파"));
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of(low, high));
        given(recipeRequiredProductRepository.findByRecipeIdIn(anyList())).willReturn(required);
        given(fridgeItemRepository.findByMemberIdAndIsDeletedFalse(1L)).willReturn(fridge);
        given(productRepository.findByProductIdInAndIsActiveTrue(anyList())).willReturn(products);

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, false, false, 0, 10);

        assertThat(result.content()).extracting(RecipeRecommendationResponse::recipeId)
                .containsExactly(1L, 2L);
        assertThat(result.content().get(0).matchRate()).isEqualTo(100);
    }

    @Test
    @DisplayName("추천 가능한 레시피가 없으면 빈 목록을 반환한다")
    void recommend_returnsEmptyWhenNoCandidates() {
        givenMember(1L);
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of());

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, false, false, 0, 10);

        assertThat(result.totalElements()).isZero();
        assertThat(result.content()).isEmpty();
    }

    @Test
    @DisplayName("favorite=true 이면 즐겨찾기한 레시피만 조회한다")
    void recommend_favoriteOnly() {
        givenMember(1L);
        Recipe favorited = recipe(1L, "즐겨찾기요리", "한식", LocalDateTime.now().minusDays(1));
        Recipe other = recipe(2L, "일반요리", "한식", LocalDateTime.now());
        List<RecipeRequiredProduct> required = List.of(required(1L, 10L), required(2L, 10L));
        List<FridgeItem> fridge = List.of(fridgeItem(10L, null));
        List<Product> products = List.of(product(10L, "김치"));
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of(favorited, other));
        given(recipeFavoriteRepository.findRecipeIdsByMemberId(1L)).willReturn(List.of(1L));
        given(recipeRequiredProductRepository.findByRecipeIdIn(List.of(1L))).willReturn(required);
        given(fridgeItemRepository.findByMemberIdAndIsDeletedFalse(1L)).willReturn(fridge);
        given(productRepository.findByProductIdInAndIsActiveTrue(anyList())).willReturn(products);

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, true, false, 0, 10);

        assertThat(result.content()).extracting(RecipeRecommendationResponse::recipeId).containsExactly(1L);
    }

    @Test
    @DisplayName("match80Only=true 이면 보유율 80% 미만 레시피는 제외한다")
    void recommend_match80Only() {
        givenMember(1L);
        Recipe full = recipe(1L, "보유100요리", "한식", LocalDateTime.now().minusDays(1));
        Recipe half = recipe(2L, "보유50요리", "한식", LocalDateTime.now());
        List<RecipeRequiredProduct> required = List.of(
                required(1L, 10L),
                required(2L, 10L), required(2L, 30L));
        List<FridgeItem> fridge = List.of(fridgeItem(10L, null));
        List<Product> products = List.of(product(10L, "김치"), product(30L, "대파"));
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of(full, half));
        given(recipeRequiredProductRepository.findByRecipeIdIn(anyList())).willReturn(required);
        given(fridgeItemRepository.findByMemberIdAndIsDeletedFalse(1L)).willReturn(fridge);
        given(productRepository.findByProductIdInAndIsActiveTrue(anyList())).willReturn(products);

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, false, true, 0, 10);

        assertThat(result.content()).extracting(RecipeRecommendationResponse::recipeId).containsExactly(1L);
        assertThat(result.content().get(0).matchRate()).isEqualTo(100);
    }

    @Test
    @DisplayName("선호 음식 카테고리(FoodCategory id)가 일치하면 '선호 음식과 일치' 사유가 붙는다")
    void recommend_matchesFavoriteFoodCategoryById() {
        Member member = givenMember(1L);
        // 회원 선호 FoodCategory id = 5
        com.naengpa.naengpamasterbackend.member.entity.FoodCategory favoriteFood =
                Mockito.mock(com.naengpa.naengpamasterbackend.member.entity.FoodCategory.class);
        given(favoriteFood.getId()).willReturn(5L);
        com.naengpa.naengpamasterbackend.member.entity.MemberFavoriteFood favorite =
                Mockito.mock(com.naengpa.naengpamasterbackend.member.entity.MemberFavoriteFood.class);
        given(favorite.getFoodCategory()).willReturn(favoriteFood);
        given(memberFavoriteFoodRepository.findAllByMemberOrderByIdAsc(member)).willReturn(List.of(favorite));

        Recipe recipe = recipe(15L, "김치볶음밥", "한식", LocalDateTime.now());
        com.naengpa.naengpamasterbackend.member.entity.FoodCategory recipeFood =
                Mockito.mock(com.naengpa.naengpamasterbackend.member.entity.FoodCategory.class);
        given(recipeFood.getId()).willReturn(5L);
        given(recipe.getFoodCategory()).willReturn(recipeFood);
        List<RecipeRequiredProduct> required = List.of(required(15L, 10L));
        List<FridgeItem> fridge = List.of(fridgeItem(10L, null));
        List<Product> products = List.of(product(10L, "김치"));
        given(recipeRepository.findRecommendationCandidates()).willReturn(List.of(recipe));
        given(recipeRequiredProductRepository.findByRecipeIdIn(List.of(15L))).willReturn(required);
        given(fridgeItemRepository.findByMemberIdAndIsDeletedFalse(1L)).willReturn(fridge);
        given(productRepository.findByProductIdInAndIsActiveTrue(anyList())).willReturn(products);

        PageResponse<RecipeRecommendationResponse> result =
                recipeRecommendationService.recommend(EMAIL, null, false, false, 0, 10);

        assertThat(result.content().get(0).recommendReasons()).contains("선호 음식과 일치");
    }

    @Test
    @DisplayName("회원 정보가 없으면 401 예외를 던진다")
    void recommend_throwsUnauthorizedWhenMemberMissing() {
        given(memberRepository.findByEmail(EMAIL)).willReturn(Optional.empty());

        assertThatThrownBy(() -> recipeRecommendationService.recommend(EMAIL, null, false, false, 0, 10))
                .isInstanceOf(ResponseStatusException.class);
    }
}
