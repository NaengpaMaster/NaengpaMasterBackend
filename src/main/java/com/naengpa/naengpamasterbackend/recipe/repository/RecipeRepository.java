package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findByRecipeIdAndDeletedFalse(Long recipeId);

    @Query(value = """
            SELECT r.recipeId AS recipeId,
                   r.name AS name,
                   c.name AS categoryName,
                   r.difficulty AS difficulty,
                   r.cookingTime AS cookingTime,
                   (SELECT COUNT(rrp) FROM RecipeRequiredProduct rrp
                     WHERE rrp.recipeId = r.recipeId) AS ingredientCount
            FROM Recipe r
            JOIN r.category c
            WHERE r.deleted = false
            """,
            countQuery = """
                    SELECT COUNT(r) FROM Recipe r WHERE r.deleted = false
                    """)
    Page<RecipeListProjection> findRecipeList(Pageable pageable);
}
