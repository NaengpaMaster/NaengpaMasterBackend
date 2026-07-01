package com.naengpa.naengpamasterbackend.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminProductCreateRequest (
        @NotNull
        Long productCategoryId,
        @NotBlank
        String name,
        Integer defaultExpiryDays
) {


}