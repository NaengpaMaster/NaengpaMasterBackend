package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        String email,

        @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[^A-Za-z0-9]).{8,15}$",
                message = "비밀번호는 8~15자이며 영문 소문자와 특수문자를 포함해야 합니다."
        )
        String password,

        @NotBlank(message = "비밀번호 확인은 공백일 수 없습니다.")
        String passwordConfirm,

        @Pattern(regexp = "^[가-힣A-Za-z0-9 ]*$", message = "닉네임은 한글, 영문, 숫자, 공백만 사용할 수 있습니다.")
        @Size(max = 50)
        String nickname,

        HouseholdType householdType
) {
}
