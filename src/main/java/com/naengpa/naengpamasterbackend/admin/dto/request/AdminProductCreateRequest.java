package com.naengpa.naengpamasterbackend.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AdminProductCreateRequest (
        @NotNull(message = "카테고리를 선택해주세요.")
        Long productCategoryId,
        @NotBlank(message = "재료명을 입력해주세요.")
        String name,
        @PositiveOrZero(message = "기본 유통기한은 0일 이상이어야 합니다.")
        Integer defaultExpiryDays
) {


}
