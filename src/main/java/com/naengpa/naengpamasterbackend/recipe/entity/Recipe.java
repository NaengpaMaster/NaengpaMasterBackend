package com.naengpa.naengpamasterbackend.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_category_id", nullable = false)
    private RecipeCategory category;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false, length = 20)
    private Difficulty difficulty;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    private Recipe(RecipeCategory category, Long createdBy, String name,
                  String description, Integer cookingTime, Difficulty difficulty) {
        this.category = category;
        this.createdBy = createdBy;
        this.name = name;
        this.description = description;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isOwnedBy(Long memberId) {
        return this.createdBy != null && this.createdBy.equals(memberId);
    }

    public void update(RecipeCategory category, String name, String description,
                       Integer cookingTime, Difficulty difficulty) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
