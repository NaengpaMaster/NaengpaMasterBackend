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
    @DisplayName("스케줄러를 시간 기다리지 않고 수동 실행 한다. *실제 DB에 연결*")
    void schedulerManualRun() {
        dailyScoreScheduler.run();
    }
}