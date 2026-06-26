package com.naengpa.naengpamasterbackend.global.exception;

public class NicknameGenerationFailedException extends RuntimeException {

    public NicknameGenerationFailedException() {
        super("닉네임 생성에 실패했습니다. 다시 시도해주세요.");
    }
}
