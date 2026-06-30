package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeFavoriteRepository extends JpaRepository<RecipeFavorite, Long> {

    long countByRecipeId(Long recipeId);

    boolean existsByRecipeIdAndMemberId(Long recipeId, Long memberId);

    void deleteByRecipeIdAndMemberId(Long recipeId, Long memberId);
}
