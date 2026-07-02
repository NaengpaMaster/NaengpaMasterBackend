package com.naengpa.naengpamasterbackend.inquiry.dto.request;

import jakarta.validation.constraints.NotBlank;

public record InquiryRequest(
        @NotBlank(message = "title은 필수입니다.")
        String title,
        @NotBlank(message = "content는 필수입니다.")
        String content
) {}
