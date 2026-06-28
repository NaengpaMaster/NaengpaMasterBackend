package com.naengpa.naengpamasterbackend.score.service;

import com.naengpa.naengpamasterbackend.score.dto.response.ScoreHistoryResponse;
import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScoreService {

    ScoreResponse getScore(String email);

    Page<ScoreHistoryResponse> getScoreHistories(String email, Pageable pageable);
}
