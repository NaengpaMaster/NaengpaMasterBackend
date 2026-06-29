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
@Table(name = "recipe_steps")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_step_id")
    private Long recipeStepId;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Column(name = "step_no", nullable = false)
    private int stepNo;

    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    @Builder
    private RecipeStep(Long recipeId, int stepNo, String content) {
        this.recipeId = recipeId;
        this.stepNo = stepNo;
        this.content = content;
    }
}
