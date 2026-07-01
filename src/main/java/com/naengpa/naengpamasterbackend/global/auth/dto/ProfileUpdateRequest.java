package com.naengpa.naengpamasterbackend.global.auth.dto;

import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ProfileUpdateRequest(
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Size(max = 50, message = "닉네임은 50자 이하여야 합니다.")
        String nickname,

        @NotNull(message = "householdType은 필수입니다.")
        HouseholdType householdType,
        List<String> favoriteFoods,
        List<Long> avoidProductIds
) {
}
