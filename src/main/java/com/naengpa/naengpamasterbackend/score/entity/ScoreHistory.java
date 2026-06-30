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
    private Long scoreHistoryId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "score_reason", length = 100)
    private ScoreReason scoreReason;

    @Column(name = "target_type", length = 100)
    private String targetType;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "score_delta", nullable = false)
    private Integer scoreDelta;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public static ScoreHistory create(Long memberId, ScoreReason reason, String targetType, Long targetId, Integer delta){
        ScoreHistory history = new ScoreHistory();
        history.memberId = memberId;
        history.scoreReason = reason;
        history.targetType = targetType;
        history.targetId = targetId;
        history.scoreDelta = delta;
        return history;
    }

}