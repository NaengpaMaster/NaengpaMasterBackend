package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {

    List<RecipeStep> findByRecipeIdAndDeletedFalseOrderByStepNoAsc(Long recipeId);

    @Modifying
    @Query("DELETE FROM RecipeStep rs WHERE rs.recipeId = :recipeId")
    void deleteByRecipeId(@Param("recipeId") Long recipeId);

}
