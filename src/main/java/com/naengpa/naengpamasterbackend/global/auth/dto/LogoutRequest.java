package com.naengpa.naengpamasterbackend.global.auth.dto;

public record LogoutRequest(
        String refreshToken
) {
}
