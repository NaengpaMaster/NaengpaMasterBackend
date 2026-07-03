package com.naengpa.naengpamasterbackend.admin.repository;

import com.naengpa.naengpamasterbackend.statistics.entity.ExpiredProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdminStatisticsRepository extends JpaRepository<ExpiredProduct, Long> {

    // 유통기한 만료 건수 조회 // 주간 만료 추이 (날짜별 만료 건수)
    @Query("SELECT COUNT(ep) FROM ExpiredProduct ep WHERE ep.createdAt BETWEEN :startDate AND :endDate")
    Long countByCreatedAtBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 카테고리별 만료량 조회
    @Query("SELECT ep.categoryName, COUNT(ep) FROM ExpiredProduct ep " +
            "WHERE ep.createdAt >= :startDate " +
            "GROUP BY ep.categoryName ORDER BY COUNT(ep) DESC")
    List<Object[]> findExpiredCountByCategory(@Param("startDate") LocalDate startDate);

    // top 5 만료 재료 조회 (7일)
    @Query("SELECT ep.productName, COUNT(ep) as cnt FROM ExpiredProduct ep WHERE ep.createdAt BETWEEN :startDate AND :endDate GROUP BY ep.productName ORDER BY cnt DESC")
    List<Object[]> findTop5ExpiredIngredientsBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

}
