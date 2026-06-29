package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeCreateRequest;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeCreateResponse;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeCategory;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeRequiredProduct;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeStep;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeCategoryRepository;
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
    private final RecipeRequiredProductRepository recipeRequiredProductRepository;
    private final RecipeStepRepository recipeStepRepository;
    private final MemberRepository memberRepository;

    public RecipeCreateResponse createRecipe(String email, RecipeCreateRequest request) {
        Long memberId = resolveMemberId(email);
        RecipeCategory category = recipeCategoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 카테고리입니다."));

        Recipe recipe = recipeRepository.save(
                Recipe.builder()
                        .category(category)
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

    private Long resolveMemberId(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 정보가 유효하지 않습니다."))
                .getId();
    }
}
