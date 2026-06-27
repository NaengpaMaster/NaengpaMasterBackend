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
@Table(name = "recipe_categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_category_id")
    private Long recipeCategoryId;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;
}
