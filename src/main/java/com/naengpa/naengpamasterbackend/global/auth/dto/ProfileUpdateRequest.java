package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProfileUpdateRequest(
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Pattern(regexp = "^[가-힣A-Za-z0-9 ]+$", message = "닉네임은 한글, 영문, 숫자, 공백만 사용할 수 있습니다.")
        @Size(max = 50, message = "닉네임은 50자 이하여야 합니다.")
        String nickname,

        HouseholdType householdType,
        List<String> favoriteFoods,
        List<Long> avoidProductIds
) {
}
