package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findByRecipeIdAndDeletedFalse(Long recipeId);

    @Query("""
            SELECT r FROM Recipe r
            JOIN FETCH r.category
            LEFT JOIN FETCH r.foodCategory
            WHERE r.deleted = false
              AND (:keyword IS NULL
                   OR r.name LIKE CONCAT('%', :keyword, '%')
                   OR EXISTS (SELECT 1 FROM RecipeRequiredProduct rrp
                              JOIN Product p ON p.productId = rrp.productId
                              WHERE rrp.recipeId = r.recipeId
                                AND p.name LIKE CONCAT('%', :keyword, '%')))
            """)
    List<Recipe> findRecommendationCandidates(@Param("keyword") String keyword);

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
