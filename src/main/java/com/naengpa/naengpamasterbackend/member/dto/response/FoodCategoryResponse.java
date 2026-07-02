package com.naengpa.naengpamasterbackend.member.dto.response;

import com.naengpa.naengpamasterbackend.member.entity.FoodCategory;

public record FoodCategoryResponse(
        Long foodCategoryId,
        String name
) {
    public static FoodCategoryResponse from(FoodCategory category) {
        return new FoodCategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
