package com.naengpa.naengpamasterbackend.global.auth.service;

import com.naengpa.naengpamasterbackend.global.auth.entity.EmailVerification;
import com.naengpa.naengpamasterbackend.global.auth.repository.EmailVerificationRepository;
import com.naengpa.naengpamasterbackend.global.exception.EmailVerificationException;
import com.naengpa.naengpamasterbackend.global.exception.WithdrawnEmailException;
import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmailVerificationServiceTest {

    @Mock
    private EmailVerificationRepository emailVerificationRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JavaMailSender mailSender;

    private EmailVerificationService emailVerificationService;

    @BeforeEach
    void setUp() {
        emailVerificationService = new EmailVerificationService(
                emailVerificationRepository,
                memberRepository,
                mailSender
        );
    }

    @Test
    @DisplayName("인증 코드를 5회 잘못 입력하면 해당 코드는 재발급이 필요하다")
    void confirmVerificationCode_exceedsAttemptsOnFifthWrongCode() {
        String email = "test@example.com";
        EmailVerification emailVerification = EmailVerification.create(
                email,
                "123456",
                LocalDateTime.now().plusMinutes(10)
        );
        given(emailVerificationRepository.findFirstByEmailOrderByCreatedAtDesc(email))
                .willReturn(Optional.of(emailVerification));

        for (int i = 0; i < 4; i++) {
            assertThatThrownBy(() -> emailVerificationService.confirmVerificationCode(email, "000000"))
                    .isInstanceOf(EmailVerificationException.class)
                    .hasMessage("인증 코드가 일치하지 않습니다.");
        }

        assertThatThrownBy(() -> emailVerificationService.confirmVerificationCode(email, "000000"))
                .isInstanceOf(EmailVerificationException.class)
                .hasMessage("인증 코드 입력 횟수를 초과했습니다. 다시 발송해주세요.");
        assertThat(emailVerification.getAttemptCount()).isEqualTo(5);
        assertThat(emailVerification.isVerified()).isFalse();

        assertThatThrownBy(() -> emailVerificationService.confirmVerificationCode(email, "123456"))
                .isInstanceOf(EmailVerificationException.class)
                .hasMessage("인증 코드 입력 횟수를 초과했습니다. 다시 발송해주세요.");
        assertThat(emailVerification.isVerified()).isFalse();
    }

    @Test
    @DisplayName("탈퇴 회원 이메일로 인증 코드 발송을 요청하면 가입 이력 안내 메시지를 반환한다")
    void sendVerificationCode_withInactiveMemberEmail_throwsWithdrawnEmailException() {
        String email = "withdrawn@example.com";
        Member inactiveMember = Member.createUser(
                email,
                "encoded-password",
                "탈퇴회원",
                HouseholdType.ETC
        );
        inactiveMember.updateStatus(MemberStatus.INACTIVE);
        given(memberRepository.findByEmail(email)).willReturn(Optional.of(inactiveMember));

        assertThatThrownBy(() -> emailVerificationService.sendVerificationCode(email))
                .isInstanceOf(WithdrawnEmailException.class)
                .hasMessage("가입 이력이 있는 이메일입니다. 관리자에게 문의해주세요.");
    }
}
