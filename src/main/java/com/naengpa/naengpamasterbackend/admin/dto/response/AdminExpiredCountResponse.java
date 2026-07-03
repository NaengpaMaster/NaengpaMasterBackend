package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminExpiredCountResponse(Long thisWeekCount,
                                        Long lastWeekCount,
                                        Double weekChangePct) {
}
