package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminMemberResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminMemberService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/admin/members")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    // 관리자 회원 목록 조회 API(관리자 목록, 회원 목록, 탈퇴 회원 목록)
    @GetMapping
    public ResponseEntity<ApiResponse<Page<AdminMemberResponse>>> getMembers(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam MemberRole role,
            @RequestParam MemberStatus status,
            @RequestParam(required = false) String search) {

        Page<AdminMemberResponse> members = adminMemberService.getMembers(role, status, search, pageable);

        return ResponseEntity.ok(ApiResponse.success(members));
    }



}
