package com.naengpa.naengpamasterbackend.global.exception;

public class EmailVerificationException extends RuntimeException {

    public EmailVerificationException(String message) {
        super(message);
    }
}
