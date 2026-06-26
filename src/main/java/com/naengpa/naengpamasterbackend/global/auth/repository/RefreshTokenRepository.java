package com.naengpa.naengpamasterbackend.global.auth.repository;

import com.naengpa.naengpamasterbackend.global.auth.entity.RefreshToken;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    List<RefreshToken> findAllByMemberAndExpiredAtAfter(Member member, LocalDateTime now);
}
