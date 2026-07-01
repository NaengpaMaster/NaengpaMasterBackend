package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.repository.IngredientView;

import java.util.List;

public record AdminRecipeDetailResponse(
        Long recipeId,
        String recipeName,
        String description,
        String category,
        Integer cookTime,
        String difficulty,
        List<Ingredient> ingredients,
        List<Step> steps
) {

    public static AdminRecipeDetailResponse of(Recipe recipe,
                                               List<IngredientView> ingredients,
                                               List<Step> steps) {
        return new AdminRecipeDetailResponse(
                recipe.getRecipeId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getCategory().getName(),
                recipe.getCookingTime(),
                recipe.getDifficulty().name(),
                ingredients.stream()
                        .map(i -> new Ingredient(i.getProductId(), i.getName()))
                        .toList(),
                steps
        );
    }

    public record Ingredient(Long ingredientId, String ingredientName) {
    }

    public record Step(int stepOrder, String content) {
    }
}
