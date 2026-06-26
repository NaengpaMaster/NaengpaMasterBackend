package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;

import java.time.LocalDateTime;

public record MemberResponse(
        Long memberId,
        String email,
        String nickname,
        MemberRole role,
        HouseholdType householdType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt,
        MemberStatus status
) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getRole(),
                member.getHouseholdType(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getDeletedAt(),
                member.getStatus()
        );
    }
}
