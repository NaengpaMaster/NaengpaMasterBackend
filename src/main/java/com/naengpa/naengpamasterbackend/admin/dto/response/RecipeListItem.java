package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.recipe.entity.Difficulty;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeListProjection;

public record RecipeListItem(
        Long recipeId,
        String name,
        String categoryName,
        String difficulty,
        String difficultyLabel,
        Integer cookingTime,
        long ingredientCount
) {
    public static RecipeListItem from(RecipeListProjection p) {
        Difficulty difficulty = p.getDifficulty();
        return new RecipeListItem(
                p.getRecipeId(),
                p.getName(),
                p.getCategoryName(),
                difficulty.name(),
                difficulty.getLabel(),
                p.getCookingTime(),
                p.getIngredientCount() == null ? 0L : p.getIngredientCount()
        );
    }
}
