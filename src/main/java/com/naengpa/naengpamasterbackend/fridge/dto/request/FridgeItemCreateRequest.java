package com.naengpa.naengpamasterbackend.fridge.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record FridgeItemCreateRequest (

    @NotNull
    Long productId,
    @NotBlank
    String quantity,
    LocalDate expiryDate,
    String memo
) {

        }

