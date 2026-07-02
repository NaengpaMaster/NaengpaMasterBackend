package com.naengpa.naengpamasterbackend.statistics.dto.response;

public record ExpiredProductCategoryResponse(
        String categoryName,
        Long expiredCount
) {
}
