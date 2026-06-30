package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminRecipeDetailResponse;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRequiredProductRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminRecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeStepRepository recipeStepRepository;
    private final RecipeRequiredProductRepository recipeRequiredProductRepository;

    public AdminRecipeDetailResponse getRecipeDetail(Long recipeId) {
        Recipe recipe = recipeRepository.findByRecipeIdAndDeletedFalse(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다."));

        List<AdminRecipeDetailResponse.Step> steps = recipeStepRepository
                .findByRecipeIdAndDeletedFalseOrderByStepNoAsc(recipeId).stream()
                .map(s -> new AdminRecipeDetailResponse.Step(s.getStepNo(), s.getContent()))
                .toList();

        return AdminRecipeDetailResponse.of(
                recipe,
                recipeRequiredProductRepository.findIngredients(recipeId),
                steps
        );
    }
}
