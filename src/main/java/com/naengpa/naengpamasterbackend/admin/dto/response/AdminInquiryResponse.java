package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;

import java.time.LocalDateTime;

public record AdminInquiryResponse(Long inquiryId,
                                   Long memberId,
                                   String nickname,
                                   String title,
                                   Boolean isAnswered,
                                   LocalDateTime createdAt) {

    public static AdminInquiryResponse from(Inquiry inquiry, String nickname) {
        return new AdminInquiryResponse(
                inquiry.getId(),
                inquiry.getMemberId(),
                nickname,
                inquiry.getTitle(),
                inquiry.getIsAnswered(),
                inquiry.getCreatedAt()
        );
    }
}
