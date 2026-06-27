package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.Difficulty;

public interface RecipeListProjection {

    Long getRecipeId();

    String getName();

    String getCategoryName();

    Difficulty getDifficulty();

    Integer getCookingTime();

    Long getIngredientCount();
}
