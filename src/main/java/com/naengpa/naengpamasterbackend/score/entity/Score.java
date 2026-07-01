package com.naengpa.naengpamasterbackend.score.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "scores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private static final long INITIAL_GRADE_ID = 1L;
    private static final int INITIAL_SCORE = 10;
    private static final int MAX_SCORE = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;

    @Column(name = "member_id", nullable = false, unique = true)
    private Long memberId;

    // grade_id 미사용
    //@Column(name = "grade_id", nullable = false, columnDefinition = "bigint default 1")
    //private Long gradeId;

    @Column(name = "score")
    private int score;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void updateScore(int newScore){
        this.score = newScore;
        this.updatedAt = LocalDateTime.now();
    }

    public static Score createInitial(Long memberId) {
        Score score = new Score();
        score.memberId = memberId;
        score.gradeId = INITIAL_GRADE_ID;
        score.score = INITIAL_SCORE;
        score.updatedAt = LocalDateTime.now();
        return score;
    }

    public int addScore(int delta) {
        int before = this.score;
        this.score = Math.min(this.score + delta, MAX_SCORE);
        this.updatedAt = LocalDateTime.now();
        return this.score - before;
    }
}
