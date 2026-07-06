package com.naengpa.naengpamasterbackend.fridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FridgeItemUpdateRequest(
        @NotNull(message = "재료를 선택해주세요.")
        Long productId,

        @NotBlank(message = "수량을 입력해주세요.")
        String quantity,

        LocalDate expiryDate,

        String memo
) {
}
