package com.naengpa.naengpamasterbackend.global.exception;

public class CommentAccessDeniedException extends RuntimeException {
    public CommentAccessDeniedException(String message) {
        super(message);
    }
}
