package com.naengpa.naengpamasterbackend.global.exception;

public class InquiryAnswerNotFoundException extends RuntimeException {
    public InquiryAnswerNotFoundException() {
        super("문의 답변을 찾을 수 없습니다.");
    }
}
