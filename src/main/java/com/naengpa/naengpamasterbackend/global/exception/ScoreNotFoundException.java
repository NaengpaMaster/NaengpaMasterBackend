package com.naengpa.naengpamasterbackend.global.exception;

public class ScoreNotFoundException extends RuntimeException {
    public ScoreNotFoundException() {
        super("점수 정보를 찾을 수 없습니다.");
    }
}
