package com.naengpa.naengpamasterbackend.admin.dto.request;

import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import jakarta.validation.constraints.NotNull;

public record AdminMemberStatusRequest(
        @NotNull(message = "status는 필수입니다.")
        MemberStatus status
) {}
