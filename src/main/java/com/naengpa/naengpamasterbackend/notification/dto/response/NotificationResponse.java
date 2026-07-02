package com.naengpa.naengpamasterbackend.notification.dto.response;

import com.naengpa.naengpamasterbackend.notification.entity.Notification;
import com.naengpa.naengpamasterbackend.notification.entity.NotificationTargetType;
import com.naengpa.naengpamasterbackend.notification.entity.NotificationType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record NotificationResponse(
        Long notificationId,
        NotificationType type,
        String title,
        String content,
        NotificationTargetType targetType,
        Long targetId,
        LocalDate targetExpiryDate,
        LocalDateTime readAt,
        LocalDateTime createdAt
) {
    public static NotificationResponse from(Notification notification) {
        return new NotificationResponse(
                notification.getNotificationId(),
                notification.getType(),
                notification.getTitle(),
                notification.getContent(),
                notification.getTargetType(),
                notification.getTargetId(),
                notification.getTargetExpiryDate(),
                notification.getReadAt(),
                notification.getCreatedAt()
        );
    }
}
