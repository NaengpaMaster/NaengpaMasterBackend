package com.naengpa.naengpamasterbackend.statistics.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredProductCategoryResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredRecordResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.TopIngredientResponse;
import com.naengpa.naengpamasterbackend.statistics.service.MemberStatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member-stats")
@RequiredArgsConstructor
public class MemberStatController {

    private final MemberStatService memberStatService;

    //가장 많이 만료된 재료 TOP 5
    @GetMapping("/top-ingredients")
    public ResponseEntity<ApiResponse<List<TopIngredientResponse>>>
    getTop5Ingredients(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "7") int days
    ) {
        List<TopIngredientResponse> data = memberStatService.getTop5Ingredients(userDetails.getUsername(), days);
        return ResponseEntity.ok(ApiResponse.success("가장 많이 만료된 재료 TOP5 조회에 성공 했습니다.", data));
    }

    //카테고리별 만료량
    @GetMapping("/expired-categories")
    public ResponseEntity<ApiResponse<List<ExpiredProductCategoryResponse>>> getExpiredProductCategories(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "7") int days
    ) {
        List<ExpiredProductCategoryResponse> data = memberStatService.getExpiredProductCategories(userDetails.getUsername(), days);
        return ResponseEntity.ok(ApiResponse.success("카테고리별 만료량 조회에 성공 했습니다.", data));
    }

    //최근 만료 기록
    @GetMapping("/expired-records")
    public ResponseEntity<ApiResponse<List<ExpiredRecordResponse>>> getExpiredRecords(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "7") int days
    ) {
        List<ExpiredRecordResponse> data = memberStatService.getExpiredRecords(userDetails.getUsername(), days);
        return ResponseEntity.ok(ApiResponse.success("재료 만료 내역 조회에 성공 했습니다.", data));
    }

}
