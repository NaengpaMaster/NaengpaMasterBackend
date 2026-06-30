package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminProductResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminProductService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    //사전 재료 전체 조회 (활성 + 비활성)
    @GetMapping()
    public ResponseEntity<ApiResponse<List<AdminProductResponse>>> findAllProducts() {
        return ResponseEntity.ok(ApiResponse.success(adminProductService.findAllProducts()));
    }

    //비활성 사전 재료 조회
    @GetMapping("/inactive")
    public ResponseEntity<ApiResponse<List<AdminProductResponse>>> findInactiveProducts(){
        return ResponseEntity.ok(ApiResponse.success((adminProductService.findInactiveProducts())));
    }

}
