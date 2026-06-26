package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminTopWastedIngredientResponse(Integer rank,
                                               Long productId,
                                               String productName,
                                               Long discardedCount,
                                               Integer rankChange) {
}
