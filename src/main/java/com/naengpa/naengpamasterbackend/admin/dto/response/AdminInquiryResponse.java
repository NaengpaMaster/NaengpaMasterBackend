package com.naengpa.naengpamasterbackend.admin.dto.response;

import java.time.LocalDateTime;

public record AdminInquiryResponse(Long inquiryId,
                                   Long memberId,
                                   String title,
                                   String nickname,
                                   Boolean isAnswered,
                                   LocalDateTime createdAt) {
}
