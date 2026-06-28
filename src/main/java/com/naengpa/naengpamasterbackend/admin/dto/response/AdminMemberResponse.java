package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;

import java.time.LocalDateTime;

public record AdminMemberResponse(Long memberId,
                                  String email,
                                  String nickname,
                                  String householdType,
                                  MemberStatus status,
                                  LocalDateTime createdAt) {

    public static AdminMemberResponse from(Member member) {
        return new AdminMemberResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getHouseholdType().name(),
                member.getStatus(),
                member.getCreatedAt()
        );
    }
}
