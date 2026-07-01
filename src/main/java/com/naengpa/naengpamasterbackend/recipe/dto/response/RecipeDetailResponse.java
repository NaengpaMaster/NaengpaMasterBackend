package com.naengpa.naengpamasterbackend.recipe.dto.response;

import java.util.List;

public record RecipeDetailResponse(
        Long recipeId,
        String recipeName,
        String description,
        String category,
        Integer cookTime,
        String difficulty,
        long likeCount,
        Boolean liked,
        boolean canManage,
        List<IngredientItem> ingredients,
        List<String> missingIngredients,
        List<StepItem> steps
) {

    public record IngredientItem(
            Long ingredientId,
            String ingredientName,
            boolean owned
    ) {
    }

    public record StepItem(
            Integer stepOrder,
            String content
    ) {
    }
}
