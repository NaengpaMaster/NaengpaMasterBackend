package com.naengpa.naengpamasterbackend.recipe.repository;

public interface RecipeLikeCountProjection {

    Long getRecipeId();

    Long getLikeCount();
}
