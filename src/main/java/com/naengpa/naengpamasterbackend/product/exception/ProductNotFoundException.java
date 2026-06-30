package com.naengpa.naengpamasterbackend.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super(productId == null
                ? "존재하지 않는 재료가 포함되어 있습니다."
                : "존재하지 않는 재료입니다. productId = " + productId);
    }
}
