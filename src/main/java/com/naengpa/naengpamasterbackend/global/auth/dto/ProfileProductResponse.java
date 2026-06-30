package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.product.entity.Product;

public record ProfileProductResponse(
        Long productId,
        Long productCategoryId,
        String name
) {

    public static ProfileProductResponse from(Product product) {
        return new ProfileProductResponse(
                product.getProductId(),
                product.getProductCategoryId(),
                product.getName()
        );
    }
}
