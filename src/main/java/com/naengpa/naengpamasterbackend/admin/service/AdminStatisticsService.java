package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminExpiredCountResponse;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminScoreAverageResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminMemberRepository;
import com.naengpa.naengpamasterbackend.admin.repository.AdminScoreRepository;
import com.naengpa.naengpamasterbackend.admin.repository.AdminStatisticsRepository;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
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

        return new AdminExpiredCountResponse(thisWeekCount, lastWeekCount, startOfWeek);
    }

    // 주간 만료 추이 (날짜별 만료 건수)
    @Transactional(readOnly = true)
    public List<AdminExpiredCountResponse> getWeeklyExpiredTrend() {
        LocalDate startDate = null;

        for (int i = 5; i >= 0; i--) {
            LocalDate start = LocalDate.now().minusWeeks(i).with(DayOfWeek.MONDAY);
            LocalDate end = LocalDate.now().minusWeeks(i).with(DayOfWeek.SUNDAY);
            Long count = adminStatisticsRepository.countByCreatedAtBetween(start, end);
            // 결과 리스트에 추가
        }

        return new ArrayList<>();
    }

    // top 5 유통기한 만료 재료 조회


    // 카테고리별 만료량 조회

}
