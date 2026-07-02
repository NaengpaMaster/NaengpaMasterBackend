package com.naengpa.naengpamasterbackend.statistics.dto.response;

import java.time.LocalDate;

public record ExpiredRecordResponse(
        String ingredientName,
        Long productCategoryId,
        LocalDate expiredDate
) {
}
