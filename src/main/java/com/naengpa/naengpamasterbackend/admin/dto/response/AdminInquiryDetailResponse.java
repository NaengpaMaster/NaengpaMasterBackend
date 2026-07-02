package com.naengpa.naengpamasterbackend.admin.dto.response;

import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;
import com.naengpa.naengpamasterbackend.inquiry.entity.InquiryAnswer;

import java.time.LocalDateTime;

public record AdminInquiryDetailResponse(Long inquiryId,
                                         Long memberId,
                                         String title,
                                         String content,
                                         String nickname,
                                         Boolean isAnswered,
                                         LocalDateTime createdAt,
                                         Long answerId,
                                         String answerContent,
                                         Long answeredBy,
                                         LocalDateTime answeredAt) {

    public static AdminInquiryDetailResponse from(Inquiry inquiry, InquiryAnswer inquiryAnswer, String nickname) {
        return new AdminInquiryDetailResponse(
                inquiry.getId(),
                inquiry.getMemberId(),
                inquiry.getTitle(),
                inquiry.getContent(),
                nickname,
                inquiry.getIsAnswered(),
                inquiry.getCreatedAt(),
                inquiryAnswer != null ? inquiryAnswer.getId() : null,
                inquiryAnswer != null ? inquiryAnswer.getContent() : null,
                inquiryAnswer != null ? inquiryAnswer.getCreatedBy() : null,
                inquiryAnswer != null ? inquiryAnswer.getCreatedAt() : null
        );
    }
}
