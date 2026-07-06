package com.naengpa.naengpamasterbackend.shopping.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record ShoppingItemCreateRequest (

    @NotNull(message = "재료를 선택해주세요.")
    Long productId,

    @NotBlank(message = "수량을 입력해주세요.")
    String quantity
) {}
