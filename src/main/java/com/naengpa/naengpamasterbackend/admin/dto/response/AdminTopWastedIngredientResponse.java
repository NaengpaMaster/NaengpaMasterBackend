package com.naengpa.naengpamasterbackend.admin.dto.response;

public record AdminTopWastedIngredientResponse(Integer rank,
                                               String productName,
                                               Long discardedCount,
                                               Integer rankChange) {
}
