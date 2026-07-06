package com.naengpa.naengpamasterbackend.score.controller;

import com.naengpa.naengpamasterbackend.score.scheduler.DailyScoreScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManualScoreController {

    private final DailyScoreScheduler dailyScoreScheduler;

    @PostMapping("/api/v1/admin/scores/run-scheduler")
    public ResponseEntity<String> runScoreScheduler() {
        dailyScoreScheduler.run();
        return ResponseEntity.ok("스케줄러 실행 완료");
    }
}
