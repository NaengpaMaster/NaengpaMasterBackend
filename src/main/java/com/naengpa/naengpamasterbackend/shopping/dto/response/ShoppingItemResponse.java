package com.naengpa.naengpamasterbackend.shopping.dto.response;

import com.naengpa.naengpamasterbackend.shopping.entity.ShoppingItem;


public record ShoppingItemResponse (
        Long shoppingItemId,
        Long memberId,
        Long productId,
        String quantity,
        Boolean isPurchased
){
    public static ShoppingItemResponse from(ShoppingItem shoppingItem) {
        return new ShoppingItemResponse(
                shoppingItem.getShoppingItemId(),
                shoppingItem.getMemberId(),
                shoppingItem.getProductId(),
                shoppingItem.getQuantity(),
                shoppingItem.getIsPurchased()
        );
    }
}
