package com.naengpa.naengpamasterbackend.global.exception;

public class ShoppingItemNotFoundException extends RuntimeException {

    public ShoppingItemNotFoundException() {
        super("장보기 항목을 찾을 수 없습니다.");
    }
}
