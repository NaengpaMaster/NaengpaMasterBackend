package com.naengpa.naengpamasterbackend.score.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.score.dto.response.ScoreHistoryResponse;
import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;
import com.naengpa.naengpamasterbackend.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public ResponseEntity<ApiResponse<ScoreResponse>> getScores(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();

        return ResponseEntity.ok(
                ApiResponse.success("회원 점수 조회에 성공 했습니다.", scoreService.getScore(email))
        );
    }

    @GetMapping("/histories")
    public ResponseEntity<ApiResponse<Page<ScoreHistoryResponse>>> getScoreHistories(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 20) Pageable pageable) {

        String email = userDetails.getUsername();
        return ResponseEntity.ok(
                ApiResponse.success("회원 점수 산정 내역 조회에 성공 했습니다.", scoreService.getScoreHistories(email, pageable))
        );
    }

}
