package com.naengpa.naengpamasterbackend.score.scheduler;

import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemListResponse;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.notification.service.NotificationService;
import com.naengpa.naengpamasterbackend.product.repository.ProductCategoryRepository;
import com.naengpa.naengpamasterbackend.score.entity.Score;
import com.naengpa.naengpamasterbackend.score.repository.ScoreHistoryRepository;
import com.naengpa.naengpamasterbackend.score.repository.ScoreRepository;
import com.naengpa.naengpamasterbackend.statistics.repository.ExpiredProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DailyScoreSchedulerMockTest {

    @Mock MemberRepository memberRepository;
    @Mock
    FridgeItemService fridgeItemService;
    @Mock ScoreRepository scoreRepository;
    @Mock ScoreHistoryRepository scoreHistoryRepository;
    @Mock ExpiredProductRepository expiredProductRepository;
    @Mock ProductCategoryRepository productCategoryRepository;
    @Mock NotificationService notificationService;

    @InjectMocks
    DailyScoreScheduler scheduler;

    @Test
    @DisplayName("재료 미보유 회원은 유지기간 3일에서 스케줄러가 돌아도 점수가 가산되지 않는다")
    void noItems_maintenancePeriod3_shouldNotAddScore() {
        // given: maintenancePeriod = 3인 회원 생성
        Member member = Member.createUser(
                "test@test.com", "encodedPw", "테스트닉네임", HouseholdType.ETC
        );
        member.increaseMaintenancePeriod(); // 1
        member.increaseMaintenancePeriod(); // 2
        member.increaseMaintenancePeriod(); // 3

        given(memberRepository.findAllByStatusAndDeletedAtIsNull(MemberStatus.ACTIVE))
                .willReturn(List.of(member));

        given(fridgeItemService.findExpiredFridgeItems(member.getEmail()))
                .willReturn(Collections.emptyList()); // 만료 재료 없음

        given(fridgeItemService.findFridgeItem(member.getEmail()))
                .willReturn(Collections.emptyList()); // 활성 재료 자체가 없음 (미보유)

        // when
        scheduler.run();

        // then
        // 재료가 없으므로 4일째여도 점수 가산 로직이 아예 호출되지 않아야 함
        verify(scoreRepository, never()).findByMemberId(any());
        verify(scoreHistoryRepository, never()).save(any());

        // 유지기간은 증가하지 않고 그대로 3이어야 함 (else 블록에서 아무 것도 안 하므로)
        assertThat(member.getMaintenancePeriod()).isEqualTo(3);
    }

    @Test
    @DisplayName("활성 재료를 보유하고 유지기간 3일일 때 스케줄러가 돌면 4일째로 무만료유지 점수가 가산된다")
    void hasActiveItems_maintenancePeriod3_shouldAddScoreOn4thDay() {
        // given: maintenancePeriod = 3인 회원 생성
        Member member = Member.createUser(
                "test@test.com", "encodedPw", "테스트닉네임", HouseholdType.ETC
        );
        member.increaseMaintenancePeriod(); // 1
        member.increaseMaintenancePeriod(); // 2
        member.increaseMaintenancePeriod(); // 3

        Score score = Score.createInitial(member.getId()); // 초기 10점 가정

        // 활성 재료 1개 (실제 record 인스턴스로 생성)
        FridgeItemListResponse activeItem = new FridgeItemListResponse(
                1L, 100L, 10L, "감자", "3개", LocalDate.now().plusDays(5), null
        );

        given(memberRepository.findAllByStatusAndDeletedAtIsNull(MemberStatus.ACTIVE))
                .willReturn(List.of(member));

        given(fridgeItemService.findExpiredFridgeItems(member.getEmail()))
                .willReturn(Collections.emptyList()); // 만료 재료 없음

        given(fridgeItemService.findFridgeItem(member.getEmail()))
                .willReturn(List.of(activeItem)); // 활성 재료 1개 이상 보유

        given(scoreRepository.findByMemberId(member.getId()))
                .willReturn(Optional.of(score));

        // when
        scheduler.run();

        // then
        assertThat(member.getMaintenancePeriod()).isEqualTo(4);
        assertThat(score.getScore()).isEqualTo(15); // 초기 10점 + 5점
        verify(scoreHistoryRepository).save(any());
    }

}
