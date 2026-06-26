package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.member.entity.MemberRole;

public record TokenResponse(
        Long memberId,
        String email,
        String nickname,
        MemberRole role,
        String accessToken,
        String refreshToken
) {
}
