package com.naengpa.naengpamasterbackend.admin.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminAnswerRequest(
        @NotBlank(message = "답변 내용은 필수입니다.")
        String content
) {}
