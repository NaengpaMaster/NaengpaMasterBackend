package com.naengpa.naengpamasterbackend.admin.dto.response;

import java.time.LocalDateTime;

public record AdminMemberResponse(Long memberId,
                                  String email,
                                  String nickname,
                                  String householderType,
                                  Boolean isDelete,
                                  LocalDateTime createdAt) {
}
