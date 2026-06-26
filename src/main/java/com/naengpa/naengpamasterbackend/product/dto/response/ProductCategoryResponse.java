package com.naengpa.naengpamasterbackend.product.dto.response;

import com.naengpa.naengpamasterbackend.product.entity.ProductCategory;

public record ProductCategoryResponse (
        Long productCategoryId,
        String name
){
    public static ProductCategoryResponse from(ProductCategory productCategory){
        return new ProductCategoryResponse(
                productCategory.getProductCategoryId(),
                productCategory.getName()
        );
    }
}
