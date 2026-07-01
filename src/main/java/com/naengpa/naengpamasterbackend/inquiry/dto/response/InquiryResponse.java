package com.naengpa.naengpamasterbackend.inquiry.dto.response;

import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;

import java.time.LocalDateTime;

public record InquiryResponse(
    Long inquiryId,
    String title,
    Boolean isAnswered,
    LocalDateTime createdAt
) {
    public static InquiryResponse from(Inquiry inquiry) {
        return new InquiryResponse(
                inquiry.getId(),
                inquiry.getTitle(),
                inquiry.getIsAnswered(),
                inquiry.getCreatedAt()
        );
    }
}
