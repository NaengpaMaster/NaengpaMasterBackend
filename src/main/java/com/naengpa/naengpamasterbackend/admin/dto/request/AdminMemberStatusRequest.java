package com.naengpa.naengpamasterbackend.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminMemberStatusRequest {

    @NotNull
    private Boolean isDeleted;
}
