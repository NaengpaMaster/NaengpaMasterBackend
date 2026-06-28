package com.naengpa.naengpamasterbackend.score.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;
import com.naengpa.naengpamasterbackend.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
