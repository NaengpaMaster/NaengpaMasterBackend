package com.naengpa.naengpamasterbackend.global.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmailVerificationConfirmRequest(
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        String email,

        @NotBlank(message = "인증 코드는 공백일 수 없습니다.")
        @Pattern(regexp = "\\d{6}", message = "인증 코드는 6자리 숫자입니다.")
        String code
) {
}
