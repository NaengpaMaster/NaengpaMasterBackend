package com.naengpa.naengpamasterbackend.statistics.dto.response;

public record TopIngredientQueryResult(
        String ingredientName,
        Long expiredCount
) {
}
