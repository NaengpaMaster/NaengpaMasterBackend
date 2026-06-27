package com.naengpa.naengpamasterbackend.fridge.dto.response;

import java.time.LocalDate;

public record FridgeItemListResponse (
        Long fridgeItemId,
        Long productId,
        Long productCategoryId,
        String productName,
        String quantity,
        LocalDate expiryDate,
        String memo
){

}
