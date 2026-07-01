package com.naengpa.naengpamasterbackend.score.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    private Score scoreWith(int current) {
        Score score = Score.createInitial(1L); // 초기 10점
        score.addScore(current - 10);
        return score;
    }

    @Test
    @DisplayName("상한선 아래에서는 가산된 만큼 그대로 반영된다")
    void addScore_belowCap() {
        Score score = scoreWith(50);

        int applied = score.addScore(3);

        assertThat(applied).isEqualTo(3);
        assertThat(score.getScore()).isEqualTo(53);
    }

    @Test
    @DisplayName("상한선(100점)을 넘으면 100점에서 멈추고 실제 증가량만 반환한다")
    void addScore_capsAt100() {
        Score score = scoreWith(99);

        int applied = score.addScore(3);

        assertThat(applied).isEqualTo(1);
        assertThat(score.getScore()).isEqualTo(100);
    }

    @Test
    @DisplayName("이미 100점이면 증가량 0을 반환한다")
    void addScore_alreadyAtCap() {
        Score score = scoreWith(100);

        int applied = score.addScore(3);

        assertThat(applied).isZero();
        assertThat(score.getScore()).isEqualTo(100);
    }
}
