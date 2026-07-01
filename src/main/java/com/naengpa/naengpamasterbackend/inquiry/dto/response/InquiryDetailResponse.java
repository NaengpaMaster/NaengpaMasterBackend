package com.naengpa.naengpamasterbackend.inquiry.dto.response;

import com.naengpa.naengpamasterbackend.inquiry.entity.Inquiry;
import com.naengpa.naengpamasterbackend.inquiry.entity.InquiryAnswer;

import java.time.LocalDateTime;

public record InquiryDetailResponse(
        Long inquiryId,
        String title,
        String content,
        Boolean isAnswered,
        LocalDateTime createdAt,
        String answerContent,
        LocalDateTime answeredAt
) {

    public static InquiryDetailResponse from(Inquiry inquiry, InquiryAnswer inquiryAnswer) {
        return new InquiryDetailResponse(
                inquiry.getId(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getIsAnswered(),
                inquiry.getCreatedAt(),
                inquiryAnswer != null ? inquiryAnswer.getContent() : null,
                inquiryAnswer != null ? inquiryAnswer.getCreatedAt() : null
        );
    }
}
