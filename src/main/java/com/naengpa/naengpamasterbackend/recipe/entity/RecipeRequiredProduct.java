package com.naengpa.naengpamasterbackend.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe_required_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeRequiredProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_required_product_id")
    private Long recipeRequiredProductId;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Column(name = "product_id", nullable = false)
    private Long productId;
}
