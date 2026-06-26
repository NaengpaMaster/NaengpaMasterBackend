package com.naengpa.naengpamasterbackend.global.exception;

public class WithdrawnEmailException extends RuntimeException {

    public WithdrawnEmailException() {
        super("탈퇴한 이메일은 재가입할 수 없습니다. 관리자에게 문의해주세요.");
    }
}
