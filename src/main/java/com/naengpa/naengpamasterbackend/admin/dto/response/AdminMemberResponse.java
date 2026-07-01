package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;

import java.time.LocalDateTime;

public record AdminMemberResponse(Long memberId,
                                  String email,
                                  String nickname,
                                  MemberRole role,
                                  HouseholdType householdType,
                                  MemberStatus status,
                                  LocalDateTime createdAt,
                                  LocalDateTime deletedAt) {

    public static AdminMemberResponse from(Member member) {
        return new AdminMemberResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getRole(),
                member.getHouseholdType(),
                member.getStatus(),
                member.getCreatedAt(),
                member.getDeletedAt()
        );
    }
}
