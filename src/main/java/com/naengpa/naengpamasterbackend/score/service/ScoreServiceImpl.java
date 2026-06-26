package com.naengpa.naengpamasterbackend.score.service;

import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;
import com.naengpa.naengpamasterbackend.score.repository.GradeRepository;
import com.naengpa.naengpamasterbackend.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;
    private final GradeRepository gradeRepository;


    @Override
    public ScoreResponse getScore(Long memberId) {
        return null;
    }
}
