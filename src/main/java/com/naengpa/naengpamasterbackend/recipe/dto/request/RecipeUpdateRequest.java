package com.naengpa.naengpamasterbackend.recipe.dto.request;

import com.naengpa.naengpamasterbackend.recipe.entity.Difficulty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RecipeUpdateRequest(
        @NotNull(message = "레시피명을 입력해주세요.")
        @Size(min = 1, max = 30, message = "레시피명은 1~30자여야 합니다.")
        String name,

        @Size(max = 1000, message = "설명은 1000자 이하여야 합니다.")
        String description,

        Integer cookingTime,

        @NotNull(message = "난이도를 선택해주세요.")
        Difficulty difficulty,

        @NotNull(message = "카테고리를 선택해주세요.")
        Long categoryId,

        Long foodCategoryId,

        @NotEmpty(message = "필수 재료를 1개 이상 선택해주세요.")
        List<Long> productIds,

        @NotEmpty(message = "조리 과정을 1단계 이상 입력해주세요.")
        List<@Size(min = 1, max = 2000) String> steps
) {
}
