package com.naengpa.naengpamasterbackend.shopping.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ShoppingItemUpdateRequest(
        @NotBlank
        String quantity
) {
}
