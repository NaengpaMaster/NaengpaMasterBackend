package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminProductResponse(Long productId,
                                   Long productCategoryId,
                                   String name,
                                   Integer defaultExpiryDays,
                                   Boolean isActive) {
}
