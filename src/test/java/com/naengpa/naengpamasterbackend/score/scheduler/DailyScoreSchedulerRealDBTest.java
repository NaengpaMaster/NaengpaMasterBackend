package com.naengpa.naengpamasterbackend.score.scheduler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DailyScoreSchedulerRealDBTest {

    @Autowired
    private DailyScoreScheduler dailyScoreScheduler;

    @Test
    @DisplayName("스케줄러 수동 실행 (실제 DB 연결)")
    void schedulerManualRun() {
        dailyScoreScheduler.run();
    }
}