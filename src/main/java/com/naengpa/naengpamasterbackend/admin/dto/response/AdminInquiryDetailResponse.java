package com.naengpa.naengpamasterbackend.admin.dto.response;

import java.time.LocalDateTime;

public record AdminInquiryDetailResponse(Long inquiryId,
                                         Long memberId,
                                         String title,
                                         String content,
                                         String nickname,
                                         Boolean isAnswered,
                                         LocalDateTime createdAt,
                                         String answerContent,
                                         Long answeredBy,
                                         LocalDateTime answeredAt) {
}
