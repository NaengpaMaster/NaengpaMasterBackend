package com.naengpa.naengpamasterbackend.global.exception;

public class InquiryAlreadyAnsweredException extends RuntimeException {
    public InquiryAlreadyAnsweredException() {
        super("이미 답변이 완료된 문의입니다.");
    }
}
