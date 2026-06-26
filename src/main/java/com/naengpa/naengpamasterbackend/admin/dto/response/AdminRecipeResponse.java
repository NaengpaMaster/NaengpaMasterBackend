package com.naengpa.naengpamasterbackend.admin.dto.response;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public record AdminRecipeResponse(Long recipeId,
                                  String name,
                                  Boolean isDeleted,
                                  LocalDateTime createdAt) {
}
