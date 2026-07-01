package com.naengpa.naengpamasterbackend.score.service;

import com.naengpa.naengpamasterbackend.global.exception.ScoreNotFoundException;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.score.entity.Score;
import com.naengpa.naengpamasterbackend.score.entity.ScoreHistory;
import com.naengpa.naengpamasterbackend.score.entity.ScoreReason;
import com.naengpa.naengpamasterbackend.score.repository.ScoreHistoryRepository;
import com.naengpa.naengpamasterbackend.score.repository.ScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ScoreServiceImplTest {

    @Mock ScoreRepository scoreRepository;
    @Mock MemberRepository memberRepository;
    @Mock ScoreHistoryRepository scoreHistoryRepository;

    @InjectMocks
    ScoreServiceImpl scoreService;

    private Score scoreWith(int current) {
        Score score = Score.createInitial(7L); // 초기 10점
        score.addScore(current - 10);          // 원하는 점수로 맞춤
        return score;
    }

    @Test
    @DisplayName("점수를 가산하고 변동 이력을 저장한다")
    void addScore_savesHistory() {
        Score score = scoreWith(50);
        given(scoreRepository.findByMemberId(7L)).willReturn(Optional.of(score));

        scoreService.addScore(7L, ScoreReason.RECIPE_CREATED, "RECIPE", 100L, 3);

        assertThat(score.getScore()).isEqualTo(53);

        ArgumentCaptor<ScoreHistory> captor = ArgumentCaptor.forClass(ScoreHistory.class);
        verify(scoreHistoryRepository).save(captor.capture());
        ScoreHistory saved = captor.getValue();
        assertThat(saved.getMemberId()).isEqualTo(7L);
        assertThat(saved.getScoreReason()).isEqualTo(ScoreReason.RECIPE_CREATED);
        assertThat(saved.getTargetType()).isEqualTo("RECIPE");
        assertThat(saved.getTargetId()).isEqualTo(100L);
        assertThat(saved.getScoreDelta()).isEqualTo(3);
        assertThat(saved.getDescription()).isNull();
    }

    @Test
    @DisplayName("상한선(100점)을 넘지 않으며 이력에는 실제 반영된 증가량이 기록된다")
    void addScore_capsAt100() {
        Score score = scoreWith(99);
        given(scoreRepository.findByMemberId(7L)).willReturn(Optional.of(score));

        scoreService.addScore(7L, ScoreReason.RECIPE_CREATED, "RECIPE", 100L, 3);

        assertThat(score.getScore()).isEqualTo(100);

        ArgumentCaptor<ScoreHistory> captor = ArgumentCaptor.forClass(ScoreHistory.class);
        verify(scoreHistoryRepository).save(captor.capture());
        assertThat(captor.getValue().getScoreDelta()).isEqualTo(1);
    }

    @Test
    @DisplayName("이미 상한선이면 증가량 0으로 이력이 기록된다")
    void addScore_alreadyAtCap() {
        Score score = scoreWith(100);
        given(scoreRepository.findByMemberId(7L)).willReturn(Optional.of(score));

        scoreService.addScore(7L, ScoreReason.RECIPE_CREATED, "RECIPE", 100L, 3);

        assertThat(score.getScore()).isEqualTo(100);

        ArgumentCaptor<ScoreHistory> captor = ArgumentCaptor.forClass(ScoreHistory.class);
        verify(scoreHistoryRepository).save(captor.capture());
        assertThat(captor.getValue().getScoreDelta()).isZero();
    }

    @Test
    @DisplayName("점수 정보가 없으면 ScoreNotFoundException이 발생한다")
    void addScore_scoreNotFound() {
        given(scoreRepository.findByMemberId(7L)).willReturn(Optional.empty());

        assertThatThrownBy(() ->
                scoreService.addScore(7L, ScoreReason.RECIPE_CREATED, "RECIPE", 100L, 3))
                .isInstanceOf(ScoreNotFoundException.class);
    }
}
