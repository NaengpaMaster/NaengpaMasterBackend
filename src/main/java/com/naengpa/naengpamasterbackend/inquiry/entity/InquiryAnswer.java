package com.naengpa.naengpamasterbackend.inquiry.entity;

import com.naengpa.naengpamasterbackend.admin.dto.request.AdminAnswerRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry_answers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_answer_id")
    private Long id;

    @Column(name = "inquiry_id", nullable = false)
    private Long inquiryId;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
        isDeleted = false;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static InquiryAnswer create(Long inquiryId, String content, Long createdBy) {
        InquiryAnswer inquiryAnswer = new InquiryAnswer();
        inquiryAnswer.inquiryId = inquiryId;
        inquiryAnswer.content = content;
        inquiryAnswer.createdBy = createdBy;
        return inquiryAnswer;
    }

    public static void update(InquiryAnswer inquiryAnswer, String content, Long adminId) {
        inquiryAnswer.content = content;
        inquiryAnswer.updatedBy = adminId;
    }

    public static void delete(InquiryAnswer inquiryAnswer) {
        inquiryAnswer.isDeleted = true;
        inquiryAnswer.deletedAt = LocalDateTime.now();
    }
}