package com.naengpa.naengpamasterbackend.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe_food_categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeFoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_food_category_id")
    private Long recipeFoodCategoryId;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Column(name = "food_category_id", nullable = false)
    private Long foodCategoryId;

    @Builder
    private RecipeFoodCategory(Long recipeId, Long foodCategoryId) {
        this.recipeId = recipeId;
        this.foodCategoryId = foodCategoryId;
    }
}
