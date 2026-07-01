package com.naengpa.naengpamasterbackend.inquiry.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.inquiry.dto.request.InquiryRequest;
import com.naengpa.naengpamasterbackend.inquiry.dto.response.InquiryDetailResponse;
import com.naengpa.naengpamasterbackend.inquiry.dto.response.InquiryResponse;
import com.naengpa.naengpamasterbackend.inquiry.service.InquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    // 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<Page<InquiryResponse>>> getInquiries(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable
    ) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(ApiResponse.success(inquiryService.getInquiries(email, pageable)));
    }

    // 상세 조회
    @GetMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse<InquiryDetailResponse>> getInquiryDetail(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long inquiryId
    ) {
        String email = userDetails.getUsername();
        return ResponseEntity.ok(ApiResponse.success(inquiryService.getInquiryDetail(inquiryId, email)));
    }

    // 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInquiry(
            @RequestBody @Valid InquiryRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        inquiryService.createInquiry(request, email);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 수정
    @PatchMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse<Void>> updateInquiry(
            @PathVariable Long inquiryId,
            @RequestBody @Valid InquiryRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        inquiryService.updateInquiry(inquiryId, request, email);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 삭제
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<ApiResponse<Void>> deleteInquiry(
            @PathVariable Long inquiryId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        inquiryService.deleteInquiry(inquiryId, email);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
