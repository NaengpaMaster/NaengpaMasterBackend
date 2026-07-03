package com.naengpa.naengpamasterbackend.recipe.dto.response;

import java.util.List;

public record RecipeRecommendationResponse(
        Long recipeId,
        String recipeName,
        String description,
        String category,
        String difficulty,
        Integer cookTime,
        Integer matchRate,
        boolean expiredIngredientIncluded,
        Long likeCount,
        boolean liked,
        List<String> missingIngredients,
        List<String> recommendReasons,
        List<String> tags
) {
}
