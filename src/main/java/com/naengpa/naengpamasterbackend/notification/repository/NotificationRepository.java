package com.naengpa.naengpamasterbackend.notification.repository;

import com.naengpa.naengpamasterbackend.notification.entity.Notification;
import com.naengpa.naengpamasterbackend.notification.entity.NotificationTargetType;
import com.naengpa.naengpamasterbackend.notification.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByMemberIdAndReadAtIsNullOrderByCreatedAtDesc(Long memberId);

    Optional<Notification> findByNotificationIdAndMemberId(Long notificationId, Long memberId);

    boolean existsByMemberIdAndTypeAndTargetTypeAndTargetIdAndTargetExpiryDate(
            Long memberId,
            NotificationType type,
            NotificationTargetType targetType,
            Long targetId,
            LocalDate targetExpiryDate
    );

    boolean existsByMemberIdAndTypeAndTargetTypeAndTargetId(
            Long memberId,
            NotificationType type,
            NotificationTargetType targetType,
            Long targetId
    );
}
