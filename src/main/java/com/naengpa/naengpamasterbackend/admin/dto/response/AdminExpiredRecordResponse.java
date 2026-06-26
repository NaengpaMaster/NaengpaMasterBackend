package com.naengpa.naengpamasterbackend.admin.dto.response;

import java.time.LocalDateTime;

public record AdminExpiredRecordResponse(String productName,
                                         String categoryName,
                                         LocalDateTime createdAt) {
}
