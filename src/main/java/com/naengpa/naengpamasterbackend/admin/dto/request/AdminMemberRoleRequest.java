package com.naengpa.naengpamasterbackend.admin.dto.request;

import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import jakarta.validation.constraints.NotNull;

public record AdminMemberRoleRequest(
        @NotNull(message = "role은 필수입니다.")
        MemberRole role) {
}
