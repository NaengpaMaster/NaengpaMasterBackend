package com.naengpa.naengpamasterbackend.global.auth.service;

import com.naengpa.naengpamasterbackend.global.auth.dto.LoginRequest;
import com.naengpa.naengpamasterbackend.global.auth.dto.MemberResponse;
import com.naengpa.naengpamasterbackend.global.auth.dto.SignUpRequest;
import com.naengpa.naengpamasterbackend.global.auth.dto.TokenResponse;
import com.naengpa.naengpamasterbackend.global.auth.entity.RefreshToken;
import com.naengpa.naengpamasterbackend.global.auth.repository.RefreshTokenRepository;
import com.naengpa.naengpamasterbackend.global.exception.DuplicateEmailException;
import com.naengpa.naengpamasterbackend.global.exception.DuplicateNicknameException;
import com.naengpa.naengpamasterbackend.global.exception.NicknameGenerationFailedException;
import com.naengpa.naengpamasterbackend.global.exception.PasswordMismatchException;
import com.naengpa.naengpamasterbackend.global.exception.WithdrawnEmailException;
import com.naengpa.naengpamasterbackend.global.security.JwtTokenProvider;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final int NANOS_PER_MILLISECOND = 1_000_000;
    private static final int MAX_NICKNAME_GENERATE_ATTEMPTS = 20;

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public MemberResponse signup(SignUpRequest request) {
        if (!request.password().equals(request.passwordConfirm())) {
            throw new PasswordMismatchException();
        }

        memberRepository.findByEmail(request.email())
                .ifPresent(member -> {
                    if (member.getDeletedAt() != null) {
                        throw new WithdrawnEmailException();
                    }
                    throw new DuplicateEmailException();
                });

        Member member = Member.createUser(
                request.email(),
                passwordEncoder.encode(request.password()),
                resolveNickname(request.nickname()),
                request.householdType()
        );
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("올바르지 않은 이메일 또는 비밀번호입니다."));

        if (member.isInactive()) {
            throw new DisabledException("탈퇴 처리된 회원입니다. 관리자에게 문의해주세요.");
        }

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new BadCredentialsException("올바르지 않은 이메일 또는 비밀번호입니다.");
        }

        return issueTokens(member);
    }

    @Transactional
    public TokenResponse refreshToken(String providedRefreshToken) {
        jwtTokenProvider.validateToken(providedRefreshToken);

        RefreshToken storedToken = refreshTokenRepository.findByRefreshToken(providedRefreshToken)
                .orElseThrow(() -> new BadCredentialsException("저장된 리프레시 토큰이 없습니다."));

        if (storedToken.isExpired()) {
            refreshTokenRepository.delete(storedToken);
            throw new BadCredentialsException("리프레시 토큰이 만료되었습니다.");
        }

        Member member = storedToken.getMember();

        if (member.isInactive()) {
            storedToken.expireNow();
            throw new DisabledException("탈퇴 처리된 회원입니다. 관리자에게 문의해주세요.");
        }

        storedToken.expireNow();
        return issueTokens(member);
    }

    @Transactional
    public void logout(String refreshToken) {
        jwtTokenProvider.validateToken(refreshToken);
        refreshTokenRepository.findByRefreshToken(refreshToken)
                .ifPresent(RefreshToken::expireNow);
    }

    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        return !memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public MemberResponse getMember(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("회원을 찾을 수 없습니다."));
        return MemberResponse.from(member);
    }

    private TokenResponse issueTokens(Member member) {
        expireActiveRefreshTokens(member);

        String accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail(), member.getRole().name());

        RefreshToken tokenEntity = RefreshToken.builder()
                .member(member)
                .refreshToken(refreshToken)
                .expiredAt(LocalDateTime.now()
                        .plusNanos(jwtTokenProvider.getRefreshExpiration() * NANOS_PER_MILLISECOND))
                .build();
        refreshTokenRepository.save(tokenEntity);

        return new TokenResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getRole(),
                accessToken,
                refreshToken
        );
    }

    private void expireActiveRefreshTokens(Member member) {
        LocalDateTime now = LocalDateTime.now();
        refreshTokenRepository.findAllByMemberAndExpiredAtAfter(member, now)
                .forEach(RefreshToken::expireNow);
    }

    private String resolveNickname(String nickname) {
        if (nickname != null && !nickname.isBlank()) {
            String trimmedNickname = nickname.trim();
            if (memberRepository.existsByNickname(trimmedNickname)) {
                throw new DuplicateNicknameException();
            }
            return trimmedNickname;
        }

        for (int attempt = 0; attempt < MAX_NICKNAME_GENERATE_ATTEMPTS; attempt++) {
            String generatedNickname = Member.generateRandomNickname();
            if (!memberRepository.existsByNickname(generatedNickname)) {
                return generatedNickname;
            }
        }

        throw new NicknameGenerationFailedException();
    }
}
