package com.naengpa.naengpamasterbackend.global.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        String email,

        @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
        String password
) {
}
