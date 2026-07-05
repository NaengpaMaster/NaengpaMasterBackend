package com.naengpa.naengpamasterbackend.score.scheduler;

import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemListResponse;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.notification.service.NotificationService;
import com.naengpa.naengpamasterbackend.product.entity.ProductCategory;
import com.naengpa.naengpamasterbackend.product.repository.ProductCategoryRepository;
import com.naengpa.naengpamasterbackend.score.entity.Score;
import com.naengpa.naengpamasterbackend.score.entity.ScoreHistory;
import com.naengpa.naengpamasterbackend.score.entity.ScoreReason;
import com.naengpa.naengpamasterbackend.score.repository.ScoreHistoryRepository;
import com.naengpa.naengpamasterbackend.statistics.entity.ExpiredProduct;
import com.naengpa.naengpamasterbackend.statistics.repository.ExpiredProductRepository;
import com.naengpa.naengpamasterbackend.score.repository.ScoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyScoreScheduler {
    private final MemberRepository memberRepository;
    private final FridgeItemService fridgeItemService;
    private final ScoreRepository scoreRepository;
    private final ScoreHistoryRepository scoreHistoryRepository;
    private final ExpiredProductRepository expiredProductRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void run() {

        log.info("냉파 점수 일일 스케줄러 시작");

        List<Member> members = memberRepository.findAllByStatusAndDeletedAtIsNull(MemberStatus.ACTIVE);
        int successCount = 0;
        int failCount = 0;

        for (int i = 0; i < members.size(); i++) {

            Member member = members.get(i);
            log.info("회원 : memberId={}", member.getId());
            try {
                notificationService.createExpiryNotifications(member.getId());

                List<FridgeItemListResponse> expiredItems =
                        fridgeItemService.findExpiredFridgeItems(member.getEmail());
                List<FridgeItemListResponse> activeItems = fridgeItemService.findFridgeItem(member.getEmail());

                if (!expiredItems.isEmpty()) {
                    log.info("만료 재료 1일 보유 - EXPIRED_PRODUCT");
                    for (int j = 0; j < expiredItems.size(); j++) {

                        FridgeItemListResponse item = expiredItems.get(j);

                        ProductCategory category = productCategoryRepository
                                .findById(item.productCategoryId())
                                .orElseThrow(() -> new IllegalStateException(
                                        "카테고리ID를 찾을 수 없습니다. productCategoryId=" + item.productCategoryId()));

                        log.info("만료 재료 이력 적재");
                        expiredProductRepository.save(ExpiredProduct.create(
                                member.getId(),
                                item.productId(),
                                item.productCategoryId(),
                                item.productName(),
                                category.getName()
                        ));
                        addScore(member, item.productName(), item.productId(), item.productCategoryId(), -2, ScoreReason.EXPIRED_PRODUCT);
                    }

                    log.info("유지기간 0으로 리셋");
                    member.resetMaintenancePeriod();

                } else if(!activeItems.isEmpty()) {
                    member.increaseMaintenancePeriod();

                    if (member.getMaintenancePeriod() % 4 == 0) {
                        log.info("만료 재료 없음 && 활성 재료 보유 상태 4일 유지 - NO_EXPIRED_4DAYS");
                        addScore(member, null, null, null, 5, ScoreReason.NO_EXPIRED_4DAYS);
                    }
                } else {
                    log.info("만료된 재료 없음 || 활성 재료 없음(빈 냉장고) 상태 - 점수 미변동");
                }

                successCount++;
            } catch (Exception e) {
                failCount++;
                log.error("회원 처리 중 에러 발생 - memberId: {}, email: {}, message: {}",
                        member.getId(), member.getEmail(), e.getMessage(), e);
            }
        }

        log.info("냉파 점수 일일 스케줄러 종료 (성공: {}, 실패: {})", successCount, failCount);
    }

    private void addScore(Member member, String targetType, Long targetId, Long productCategoryId, int delta, ScoreReason reason) {

        Score score = scoreRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "점수 정보를 찾을 수 없습니다. memberId=" + member.getId()));

        log.info("점수 업데이트");
        int newScore = Math.max(0, Math.min(100, score.getScore() + delta));
        score.updateScore(newScore);

        log.info("점수 이력 적재");
        scoreHistoryRepository.save(ScoreHistory.create(
                member.getId(), reason, targetType, targetId, productCategoryId, delta
        ));
    }
}
