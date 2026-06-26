package com.naengpa.naengpamasterbackend.admin.dto.response;

import java.time.LocalDate;

public record AdminExpiredCountResponse(Long expiredCount,
                                        LocalDate startDate,
                                        LocalDate endDate) {
}
