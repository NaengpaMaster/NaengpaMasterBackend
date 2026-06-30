package com.naengpa.naengpamasterbackend.shopping.dto.response;

public record ShoppingItemListResponse(
        Long shoppingItemId,
        Long productId,
        Long productCategoryId,
        String productName,
        String quantity,
        Boolean isPurchased
) {

}