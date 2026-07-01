package com.naengpa.naengpamasterbackend.global.exception;

public class DuplicateProductNameException extends RuntimeException {
    public DuplicateProductNameException() {
        super("이미 등록된 사전 재료입니다.");
    }
}