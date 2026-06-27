package com.naengpa.naengpamasterbackend.recipe.repository;

import com.naengpa.naengpamasterbackend.recipe.entity.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {
}
