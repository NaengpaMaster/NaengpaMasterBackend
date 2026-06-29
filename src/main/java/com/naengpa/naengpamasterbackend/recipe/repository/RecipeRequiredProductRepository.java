package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeRequiredProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRequiredProductRepository extends JpaRepository<RecipeRequiredProduct, Long> {

    @Query("""
            SELECT rrp.productId AS productId, p.name AS name
            FROM RecipeRequiredProduct rrp
            JOIN Product p ON p.productId = rrp.productId
            WHERE rrp.recipeId = :recipeId
            ORDER BY p.name ASC
            """)
    List<IngredientView> findIngredients(@Param("recipeId") Long recipeId);

    List<RecipeRequiredProduct> findByRecipeIdOrderByRecipeRequiredProductIdAsc(Long recipeId);

}
