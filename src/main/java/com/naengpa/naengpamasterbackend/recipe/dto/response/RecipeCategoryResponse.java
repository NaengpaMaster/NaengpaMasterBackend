package com.naengpa.naengpamasterbackend.recipe.dto.response;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeCategory;

public record RecipeCategoryResponse(
        Long recipeCategoryId,
        String name
) {
    public static RecipeCategoryResponse from(RecipeCategory category) {
        return new RecipeCategoryResponse(
                category.getRecipeCategoryId(),
                category.getName()
        );
    }
}
