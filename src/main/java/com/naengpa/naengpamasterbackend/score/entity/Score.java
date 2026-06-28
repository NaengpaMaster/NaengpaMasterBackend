package com.naengpa.naengpamasterbackend.score.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;

    @Column(name = "member_id", nullable = false, unique = true)
    private Long memberId;

    @Column(name = "grade_id", nullable = false)
    private Long gradeId;

    @Column(name = "score")
    private int score;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
