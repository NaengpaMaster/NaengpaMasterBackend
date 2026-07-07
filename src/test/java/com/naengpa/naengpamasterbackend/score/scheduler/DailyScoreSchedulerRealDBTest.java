package com.naengpa.naengpamasterbackend.score.scheduler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("실제 DB 데이터를 변경하는 수동 스케줄러 테스트입니다. 필요할 때만 해제해서 실행하세요.")
public class DailyScoreSchedulerRealDBTest {

    @Autowired
    private DailyScoreScheduler dailyScoreScheduler;

    @Test
    @DisplayName("스케줄러 수동 실행 (실제 DB 연결)")
    void schedulerManualRun() {
        dailyScoreScheduler.run();
    }
}
