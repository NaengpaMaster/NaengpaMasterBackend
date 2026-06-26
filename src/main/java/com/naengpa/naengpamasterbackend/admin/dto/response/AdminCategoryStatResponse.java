package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminCategoryStatResponse(Long productCategoryId,
                                        String categoryName,
                                        Long expiredCount) {
}
