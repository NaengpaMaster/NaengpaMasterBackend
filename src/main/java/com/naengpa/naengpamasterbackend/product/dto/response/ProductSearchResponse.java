package com.naengpa.naengpamasterbackend.product.dto.response;

import com.naengpa.naengpamasterbackend.product.entity.Product;

public record ProductSearchResponse(
        Long productId,
        Long productCategoryId,
        String name,
        Integer defaultExpiryDays
) {
    //Product 엔티티를 ProductSearchResponse DTO로 바꾼다
    public static ProductSearchResponse from(Product product) {
        return new ProductSearchResponse(
                product.getProductId(),
                product.getProductCategoryId(),
                product.getName(),
                product.getDefaultExpiryDays()
        );
    }
}
