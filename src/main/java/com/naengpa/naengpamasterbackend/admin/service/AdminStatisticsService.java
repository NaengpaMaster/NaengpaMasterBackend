package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.response.*;
import com.naengpa.naengpamasterbackend.admin.repository.AdminMemberRepository;
import com.naengpa.naengpamasterbackend.admin.repository.AdminScoreRepository;
import com.naengpa.naengpamasterbackend.admin.repository.AdminStatisticsRepository;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminStatisticsService {

    private final AdminStatisticsRepository adminStatisticsRepository;
    private final AdminScoreRepository adminScoreRepository;
    private final AdminMemberRepository adminMemberRepository;

    // 냉파 점수 평균 조회
    @Transactional(readOnly = true)
    public AdminScoreAverageResponse getScoreAverage() {
        Long activeUserCount = adminMemberRepository.countByStatusAndRole(MemberStatus.ACTIVE, MemberRole.USER);
        Double scoreAverage = adminScoreRepository.findScoreAverage();

        return new AdminScoreAverageResponse(scoreAverage, activeUserCount);
    }

    // 유통기한 만료 건수 조회
    @Transactional(readOnly = true)
    public AdminExpiredCountResponse getExpiredCount() {
        LocalDate startOfWeek  = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY);

        LocalDate startOfLastWeek = startOfWeek.minusWeeks(1);
        LocalDate endOfLastWeek = endOfWeek.minusWeeks(1);

        Long thisWeekCount = adminStatisticsRepository.countByCreatedAtBetween(startOfWeek, endOfWeek);
        Long lastWeekCount = adminStatisticsRepository.countByCreatedAtBetween(startOfLastWeek, endOfLastWeek);

        Double weekChangePct = lastWeekCount == 0 ? null : Math.round(((double) (thisWeekCount - lastWeekCount) / lastWeekCount) * 100 * 10) / 10.0;

        return new AdminExpiredCountResponse(thisWeekCount, lastWeekCount, weekChangePct);
    }

    // 주간 만료 추이 (날짜별 만료 건수)
    @Transactional(readOnly = true)
    public AdminWeeklyTrendResponse getWeeklyExpiredTrend() {
        ArrayList<AdminWeeklyData> weeks = new ArrayList<>();

        for (int i = 5; i >= 0; i--) {
            LocalDate start = LocalDate.now().minusWeeks(i).with(DayOfWeek.MONDAY);
            LocalDate end = LocalDate.now().minusWeeks(i).with(DayOfWeek.SUNDAY);
            Long count = adminStatisticsRepository.countByCreatedAtBetween(start, end);

            String label = switch (i) {
                case 0 -> "이번주";
                case 1 -> "지난주";
                default -> i + "주전";
            };

            weeks.add(new AdminWeeklyData(label, count));
        }

       return new AdminWeeklyTrendResponse(weeks);
    }

    // top 5 유통기한 만료 재료 조회
    @Transactional(readOnly = true)
    public List<AdminTopWastedIngredientResponse> getTop5Ingredients() {
        LocalDate startDate = LocalDate.now().minusDays(30);

        return adminStatisticsRepository.findTop5ExpiredIngredients(startDate, PageRequest.of(0, 5))
                .stream()
                .map(record -> new AdminTopWastedIngredientResponse(
                        (Integer) record[0],
                        (Long) record[1],
                        (String) record[3],
                        (Long) record[4],
                        (Integer) record[5]
                ))
                .toList();
    }

    // 카테고리별 만료량 조회
    @Transactional(readOnly = true)
    public List<AdminCategoryStatResponse> getExpiredCountByCategory() {
        LocalDate startDate = LocalDate.now().minusDays(30);

        return adminStatisticsRepository.findExpiredCountByCategory(startDate)
                .stream()
                .map(row -> new AdminCategoryStatResponse(
                        (Long) row[0],
                        (String) row[1],
                        (Long) row[3]
                ))
                .toList();
    }
}
