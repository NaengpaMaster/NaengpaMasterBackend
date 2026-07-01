package com.naengpa.naengpamasterbackend.score.dto.response;

import com.naengpa.naengpamasterbackend.score.entity.ScoreReason;
import java.time.LocalDateTime;

public record ScoreHistoryResponse(

        ScoreReason scoreReason,
        String targetType,
        Integer scoreDelta,
        LocalDateTime createdAt
) {
}
