package com.naengpa.naengpamasterbackend.global.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_verification_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 6)
    private String code;

    @Column(nullable = false)
    private boolean verified;

    @Column(name = "attempt_count", nullable = false)
    private int attemptCount;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public static EmailVerification create(String email, String code, LocalDateTime expiresAt) {
        EmailVerification emailVerification = new EmailVerification();
        emailVerification.email = email;
        emailVerification.code = code;
        emailVerification.expiresAt = expiresAt;
        return emailVerification;
    }

    public boolean isExpired(LocalDateTime now) {
        return !expiresAt.isAfter(now);
    }

    public boolean canRetry() {
        return attemptCount < 5;
    }

    public void increaseAttemptCount() {
        attemptCount++;
    }

    public boolean matches(String providedCode) {
        return code.equals(providedCode);
    }

    public void verify(LocalDateTime now) {
        verified = true;
        verifiedAt = now;
    }

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
