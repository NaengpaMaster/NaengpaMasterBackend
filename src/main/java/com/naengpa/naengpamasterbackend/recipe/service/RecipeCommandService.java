package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.global.exception.RecipeNotFoundException;
import com.naengpa.naengpamasterbackend.member.entity.FoodCategory;
import com.naengpa.naengpamasterbackend.member.repository.FoodCategoryRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeCreateRequest;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeUpdateRequest;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeCreateResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeLikeResponse;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeCategory;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeFavorite;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeRequiredProduct;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeStep;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeCategoryRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeFavoriteRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRequiredProductRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeCommandService {

    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final RecipeRequiredProductRepository recipeRequiredProductRepository;
    private final RecipeStepRepository recipeStepRepository;
    private final RecipeFavoriteRepository recipeFavoriteRepository;
    private final MemberRepository memberRepository;

    public RecipeCreateResponse createRecipe(String email, RecipeCreateRequest request) {
        Long memberId = resolveMemberId(email);
        RecipeCategory category = recipeCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리입니다."));
        FoodCategory foodCategory = resolveFoodCategory(request.foodCategoryId());

        Recipe recipe = recipeRepository.save(
                Recipe.builder()
                        .category(category)
                        .foodCategory(foodCategory)
                        .createdBy(memberId)
                        .name(request.name())
                        .description(request.description())
                        .cookingTime(request.cookingTime())
                        .difficulty(request.difficulty())
                        .build()
        );
        Long recipeId = recipe.getRecipeId();

        List<RecipeRequiredProduct> products = request.productIds().stream()
                .distinct()
                .map(productId -> RecipeRequiredProduct.builder()
                        .recipeId(recipeId)
                        .productId(productId)
                        .build())
                .toList();
        recipeRequiredProductRepository.saveAll(products);

        List<String> stepContents = request.steps();
        List<RecipeStep> steps = IntStream.range(0, stepContents.size())
                .mapToObj(i -> RecipeStep.builder()
                        .recipeId(recipeId)
                        .stepNo(i + 1)
                        .content(stepContents.get(i))
                        .build())
                .toList();
        recipeStepRepository.saveAll(steps);

        return new RecipeCreateResponse(recipeId);
    }

    public void updateRecipe(Long recipeId, String email, boolean isAdmin, RecipeUpdateRequest request) {
        Long memberId = resolveMemberId(email);
        Recipe recipe = recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다."));

        if (!recipe.isOwnedBy(memberId) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인이 작성한 레시피만 수정할 수 있습니다.");
        }

        RecipeCategory category = recipeCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리입니다."));
        FoodCategory foodCategory = resolveFoodCategory(request.foodCategoryId());

        recipe.update(category, foodCategory, request.name(), request.description(),
                request.cookingTime(), request.difficulty());
    }

    private FoodCategory resolveFoodCategory(Long foodCategoryId) {
        if (foodCategoryId == null) {
            return null;
        }
        return foodCategoryRepository.findById(foodCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 음식 카테고리입니다."));
    }

    public void deleteRecipe(Long recipeId, String email, boolean isAdmin) {
        Long memberId = resolveMemberId(email);
        Recipe recipe = recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다."));

        if (!recipe.isOwnedBy(memberId) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인이 작성한 레시피만 삭제할 수 있습니다.");
        }

        recipe.softDelete();
    }

    public RecipeLikeResponse toggleLike(String email, Long recipeId) {
        Long memberId = resolveMemberId(email);
        recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(RecipeNotFoundException::new);

        boolean liked;
        if (recipeFavoriteRepository.existsByRecipeIdAndMemberId(recipeId, memberId)) {
            recipeFavoriteRepository.deleteByRecipeIdAndMemberId(recipeId, memberId);
            liked = false;
        } else {
            recipeFavoriteRepository.save(RecipeFavorite.create(recipeId, memberId));
            liked = true;
        }

        long likeCount = recipeFavoriteRepository.countByRecipeId(recipeId);
        return new RecipeLikeResponse(liked, likeCount);
    }

    private Long resolveMemberId(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 정보가 유효하지 않습니다."))
                .getId();
    }
}
