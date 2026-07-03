package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeFoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeFoodCategoryRepository extends JpaRepository<RecipeFoodCategory, Long> {

    Optional<RecipeFoodCategory> findByRecipeId(Long recipeId);

    List<RecipeFoodCategory> findByRecipeIdIn(List<Long> recipeIds);

    @Modifying
    @Query("DELETE FROM RecipeFoodCategory rfc WHERE rfc.recipeId = :recipeId")
    void deleteByRecipeId(@Param("recipeId") Long recipeId);

}
