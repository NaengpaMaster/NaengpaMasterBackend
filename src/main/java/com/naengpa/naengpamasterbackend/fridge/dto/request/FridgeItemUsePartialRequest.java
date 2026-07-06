package com.naengpa.naengpamasterbackend.fridge.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FridgeItemUsePartialRequest(
        @NotBlank(message = "남은 수량을 입력해주세요.")
        String quantity
) {
}
