package com.naengpa.naengpamasterbackend.statistics.dto.response;

public record TopIngredientResponse(
        int rank,
        String ingredientName,
        long expiredCount
) {
}
