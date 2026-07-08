package com.naengpa.naengpamasterbackend.statistics.repository;

import com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredProductCategoryResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredRecordResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.TopIngredientQueryResult;
import com.naengpa.naengpamasterbackend.statistics.entity.ExpiredProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpiredProductRepository extends JpaRepository<ExpiredProduct, Long> {

    //가장 많이 만료된 재료 TOP 5
    @Query("""
            SELECT new com.naengpa.naengpamasterbackend.statistics.dto.response.TopIngredientQueryResult(
                        e.productName, COUNT(e.productName)
            )
            FROM ExpiredProduct e
            WHERE e.memberId = :memberId
            AND e.createdAt >= :startDate            
            GROUP BY e.productName
            ORDER BY COUNT(e.productName) DESC 
            LIMIT 5          
            """)
    List<TopIngredientQueryResult> findTop5ExpiredProductByMemberId(@Param("memberId") Long memberId,
                                                                    @Param("startDate") LocalDate startDate);

    //카테고리별 만료량
    @Query("""
            SELECT new com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredProductCategoryResponse(
                        e.categoryName, COUNT(e.categoryName)
            )
            FROM ExpiredProduct e
            WHERE e.memberId = :memberId
            AND e.createdAt >= :startDate
            GROUP BY e.categoryName
            ORDER BY COUNT(e.categoryName) DESC         
            """)
    List<ExpiredProductCategoryResponse> findExpiredCategoriesByMemberId(@Param("memberId") Long memberId,
                                                                         @Param("startDate") LocalDate startDate);

    //최근 만료 기록
    @Query("""
                        SELECT new com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredRecordResponse(
                            e.productName, e.productCategoryId, e.createdAt)
                        FROM ExpiredProduct  e
                        WHERE e.memberId = :memberId
                        AND e.createdAt >= :startDate
                        ORDER BY e.createdAt DESC                              
            """)
    List<ExpiredRecordResponse> findExpiredRecordsByMemberId(@Param("memberId") Long memberId,
                                                             @Param("startDate") LocalDate startDate);
}
