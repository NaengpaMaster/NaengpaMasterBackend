package com.naengpa.naengpamasterbackend.fridge.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FridgeItemUsePartialRequest(
        @NotBlank
        String quantity
) {
}