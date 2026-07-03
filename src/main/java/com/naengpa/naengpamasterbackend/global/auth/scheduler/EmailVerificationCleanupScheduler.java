package com.naengpa.naengpamasterbackend.global.auth.scheduler;

import com.naengpa.naengpamasterbackend.global.auth.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailVerificationCleanupScheduler {

    private static final int UNVERIFIED_RETENTION_DAYS = 3;
    private static final int VERIFIED_RETENTION_DAYS = 7;

    private final EmailVerificationRepository emailVerificationRepository;

    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void cleanup() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime unverifiedCutoff = now.minusDays(UNVERIFIED_RETENTION_DAYS);
        LocalDateTime verifiedCutoff = now.minusDays(VERIFIED_RETENTION_DAYS);

        long deletedUnverified = emailVerificationRepository.deleteByVerifiedFalseAndExpiresAtBefore(unverifiedCutoff);
        long deletedVerified = emailVerificationRepository.deleteByVerifiedTrueAndVerifiedAtBefore(verifiedCutoff);

        log.info(
                "이메일 인증 이력 정리 완료 (미인증/만료: {}, 인증완료: {})",
                deletedUnverified,
                deletedVerified
        );
    }
}
