package com.naengpa.naengpamasterbackend.global.auth.repository;

import com.naengpa.naengpamasterbackend.global.auth.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    Optional<EmailVerification> findFirstByEmailOrderByCreatedAtDesc(String email);

    boolean existsByEmailAndVerifiedTrueAndVerifiedAtAfter(String email, LocalDateTime verifiedAt);

    long deleteByVerifiedFalseAndExpiresAtBefore(LocalDateTime expiresAt);

    long deleteByVerifiedTrueAndVerifiedAtBefore(LocalDateTime verifiedAt);
}
