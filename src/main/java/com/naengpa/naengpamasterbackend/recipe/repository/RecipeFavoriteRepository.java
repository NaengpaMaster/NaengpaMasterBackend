package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeFavoriteRepository extends JpaRepository<RecipeFavorite, Long> {

    long countByRecipeId(Long recipeId);

    boolean existsByRecipeIdAndMemberId(Long recipeId, Long memberId);

    void deleteByRecipeIdAndMemberId(Long recipeId, Long memberId);

    @Query("SELECT rf.recipeId FROM RecipeFavorite rf WHERE rf.memberId = :memberId")
    List<Long> findRecipeIdsByMemberId(Long memberId);

    @Query("SELECT rf.recipeId AS recipeId, COUNT(rf) AS likeCount FROM RecipeFavorite rf "
            + "WHERE rf.recipeId IN :recipeIds GROUP BY rf.recipeId")
    List<RecipeLikeCountProjection> countByRecipeIdIn(List<Long> recipeIds);
}
