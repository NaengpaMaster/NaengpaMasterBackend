package com.naengpa.naengpamasterbackend.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super("존재하지 않는 재료입니다. productId = " + productId);
    }
}
