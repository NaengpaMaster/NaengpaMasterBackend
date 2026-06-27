package com.naengpa.naengpamasterbackend.fridge.dto.response;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;

import java.time.LocalDate;

public record FridgeItemResponse(
        Long fridgeItemId,
        Long productId,
        String quantity,
        LocalDate expiryDate,
        String memo
) {
    public static FridgeItemResponse from(FridgeItem fridgeItem) {
        return new FridgeItemResponse(
                fridgeItem.getFridgeItemId(),
                fridgeItem.getProductId(),
                fridgeItem.getQuantity(),
                fridgeItem.getExpiryDate(),
                fridgeItem.getMemo()
        );
    }
}