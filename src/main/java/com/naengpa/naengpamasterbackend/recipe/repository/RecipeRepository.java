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

    @Query(value = """
            SELECT r
            FROM Recipe r
            JOIN FETCH r.category
            WHERE r.deleted = false
            """)
    List<Recipe> findRecommendationCandidates();

    @Query(value = """
            SELECT r
            FROM Recipe r
            JOIN FETCH r.category
            WHERE r.deleted = false
            AND (
                r.name LIKE CONCAT('%', :keyword, '%')
                OR EXISTS (
                            SELECT 1
                            FROM RecipeRequiredProduct rrp
                            JOIN Product p ON p.productId = rrp.productId
                            WHERE rrp.recipeId = r.recipeId
                            AND p.name LIKE CONCAT('%', :keyword, '%')
                            )
                )
            """)
    List<Recipe> searchRecommendationCandidates(@Param("keyword") String keyword);

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
            AND (CAST(:search AS string) IS NULL OR r.name LIKE CONCAT('%', CAST(:search AS string), '%'))
            """,
            countQuery = """
                    SELECT COUNT(r) FROM Recipe r
                    WHERE r.deleted = false
                    AND (CAST(:search AS string) IS NULL OR r.name LIKE CONCAT('%', CAST(:search AS string), '%'))
                    """)
    Page<RecipeListProjection> findRecipeList(@Param("search") String search, Pageable pageable);

}
