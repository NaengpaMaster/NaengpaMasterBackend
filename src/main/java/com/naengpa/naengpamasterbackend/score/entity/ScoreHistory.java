package com.naengpa.naengpamasterbackend.score.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "score_histories")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_history_id")
    private int scoreHistoryId;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    @Column(name = "target_type", length = 100)
    private String targetType;

    @Column(name = "target_id")
    private int targetId;

    @Column(name = "score_delta", nullable = false)
    private Integer scoreDelta;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}