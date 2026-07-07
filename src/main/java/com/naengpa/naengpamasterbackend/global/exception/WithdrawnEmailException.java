package com.naengpa.naengpamasterbackend.global.exception;

public class WithdrawnEmailException extends RuntimeException {

    public WithdrawnEmailException() {
        super("가입 이력이 있는 이메일입니다. 관리자에게 문의해주세요.");
    }
}
