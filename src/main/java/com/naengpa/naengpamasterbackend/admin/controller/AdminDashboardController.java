package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminDashboardResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminDashboardService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<AdminDashboardResponse>> getDashboard() {
        return ResponseEntity.ok(ApiResponse.success(adminDashboardService.getDashboard()));
    }


}
