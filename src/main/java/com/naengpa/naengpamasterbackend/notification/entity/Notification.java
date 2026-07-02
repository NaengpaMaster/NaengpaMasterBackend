package com.naengpa.naengpamasterbackend.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private NotificationType type;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", length = 50)
    private NotificationTargetType targetType;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "target_expiry_date")
    private LocalDate targetExpiryDate;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static Notification create(
            Long memberId,
            NotificationType type,
            String title,
            String content,
            NotificationTargetType targetType,
            Long targetId,
            LocalDate targetExpiryDate
    ) {
        Notification notification = new Notification();
        notification.memberId = memberId;
        notification.type = type;
        notification.title = title;
        notification.content = content;
        notification.targetType = targetType;
        notification.targetId = targetId;
        notification.targetExpiryDate = targetExpiryDate;
        return notification;
    }

    public void markAsRead() {
        if (readAt == null) {
            readAt = LocalDateTime.now();
        }
    }

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
