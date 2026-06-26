package com.naengpa.naengpamasterbackend.score.service;

import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;

public interface ScoreService {

    ScoreResponse getScore(Long memberId);

}
