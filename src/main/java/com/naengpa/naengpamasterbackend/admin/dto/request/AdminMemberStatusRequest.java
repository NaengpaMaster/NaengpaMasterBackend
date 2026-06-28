package com.naengpa.naengpamasterbackend.admin.dto.request;

import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminMemberStatusRequest {

    @NotNull
    private MemberStatus status;
}
