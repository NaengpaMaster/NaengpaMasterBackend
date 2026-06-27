package com.naengpa.naengpamasterbackend.recipe.dto.response;

import com.naengpa.naengpamasterbackend.recipe.entity.Difficulty;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeListProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public record RecipeListResponse(
        List<RecipeItem> recipes,
        long totalCount,
        int page,
        int size,
        int totalPages
) {

    public static RecipeListResponse from(Page<RecipeListProjection> page) {
        List<RecipeItem> items = page.getContent().stream()
                .map(RecipeItem::from)
                .toList();
        return new RecipeListResponse(
                items,
                page.getTotalElements(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );
    }

    public record RecipeItem(
            Long recipeId,
            String name,
            String categoryName,
            String difficulty,
            String difficultyLabel,
            Integer cookingTime,
            long ingredientCount
    ) {
        public static RecipeItem from(RecipeListProjection p) {
            Difficulty difficulty = p.getDifficulty();
            return new RecipeItem(
                    p.getRecipeId(),
                    p.getName(),
                    p.getCategoryName(),
                    difficulty.name(),
                    difficulty.getLabel(),
                    p.getCookingTime(),
                    p.getIngredientCount() == null ? 0L : p.getIngredientCount()
            );
        }
    }
}
