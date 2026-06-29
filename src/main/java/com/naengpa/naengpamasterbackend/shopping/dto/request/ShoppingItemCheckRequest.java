package com.naengpa.naengpamasterbackend.shopping.dto.request;

import jakarta.validation.constraints.NotNull;

public record ShoppingItemCheckRequest (
    @NotNull
    Boolean isPurchased
){}
