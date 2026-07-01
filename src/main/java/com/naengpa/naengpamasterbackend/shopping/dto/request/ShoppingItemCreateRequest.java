package com.naengpa.naengpamasterbackend.shopping.dto.request;


import jakarta.validation.constraints.NotNull;

public record ShoppingItemCreateRequest (

    @NotNull
    Long productId,

    String quantity
) {}
