package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.product.entity.Product;

public record AdminProductResponse(
        Long productId,
        Long productCategoryId,
        String name,
        Integer defaultExpiryDays,
        Boolean isActive
) {

    public static AdminProductResponse from(Product product) {
        return new AdminProductResponse(
                product.getProductId(),
                product.getProductCategoryId(),
                product.getName(),
                product.getDefaultExpiryDays(),
                product.getIsActive()
        );
    }
}
