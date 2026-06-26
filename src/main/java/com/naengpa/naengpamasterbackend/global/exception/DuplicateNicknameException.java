package com.naengpa.naengpamasterbackend.global.exception;

public class DuplicateNicknameException extends RuntimeException {

    public DuplicateNicknameException() {
        super("이미 사용 중인 닉네임입니다. 다시 입력해주세요.");
    }
}
