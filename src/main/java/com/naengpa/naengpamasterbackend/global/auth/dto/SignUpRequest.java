package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[^A-Za-z0-9]).{8,15}$",
                message = "비밀번호는 8~15자이며 영문 소문자와 특수문자를 포함해야 합니다."
        )
        String password,

        @NotBlank
        String passwordConfirm,

        @Size(max = 50)
        String nickname,

        HouseholdType householdType
) {
}
