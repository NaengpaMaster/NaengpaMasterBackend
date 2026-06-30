package com.naengpa.naengpamasterbackend.global.exception;

public class FoodCategoryNotFoundException extends RuntimeException {

    public FoodCategoryNotFoundException() {
        super("존재하지 않는 선호 음식이 포함되어 있습니다.");
    }
}
