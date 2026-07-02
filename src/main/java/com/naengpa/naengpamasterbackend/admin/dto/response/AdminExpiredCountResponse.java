package com.naengpa.naengpamasterbackend.admin.dto.response;

import java.time.LocalDate;

public record AdminExpiredCountResponse(Long thisWeekCount,
                                        Long lastWeekCount,
                                        Double weekChangePct) {
}
