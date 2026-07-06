package com.naengpa.naengpamasterbackend.score.service;

import com.naengpa.naengpamasterbackend.score.dto.response.ScoreHistoryResponse;
import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;
import com.naengpa.naengpamasterbackend.score.entity.ScoreReason;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScoreService {

    ScoreResponse getScore(String email);
    Page<ScoreHistoryResponse> getScoreHistories(String email, Pageable pageable);

    void addScore(Long memberId, ScoreReason reason, String targetName, Long targetId, int delta);

}
