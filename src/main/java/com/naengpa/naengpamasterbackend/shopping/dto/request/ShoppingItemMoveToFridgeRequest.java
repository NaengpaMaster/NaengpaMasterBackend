package com.naengpa.naengpamasterbackend.shopping.dto.request;

import java.time.LocalDate;

public record ShoppingItemMoveToFridgeRequest (
        LocalDate expiryDate,
        String memo
){
}
