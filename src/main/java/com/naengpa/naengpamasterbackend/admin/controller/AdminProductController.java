package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.dto.request.AdminProductCreateRequest;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminProductResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminProductService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //어드민 사전 재료 추가
    @PostMapping
    public ResponseEntity<ApiResponse<AdminProductResponse>> createProduct(
            @Valid  @RequestBody AdminProductCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "사전 재료가 등록되었습니다.", adminProductService.createProduct(request)
                ));
    }

}
