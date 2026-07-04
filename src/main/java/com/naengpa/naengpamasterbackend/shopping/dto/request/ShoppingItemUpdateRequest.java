package com.naengpa.naengpamasterbackend.shopping.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ShoppingItemUpdateRequest(
        @NotBlank(message = "수량을 입력해주세요.")
        String quantity
) {
}
