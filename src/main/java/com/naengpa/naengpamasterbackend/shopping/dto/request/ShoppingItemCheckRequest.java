package com.naengpa.naengpamasterbackend.shopping.dto.request;

import jakarta.validation.constraints.NotNull;

public record ShoppingItemCheckRequest (
    @NotNull(message = "구매 여부를 선택해주세요.")
    Boolean isPurchased
){}
