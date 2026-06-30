package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminRecipeDetailResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminRecipeService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/recipes")
@RequiredArgsConstructor
public class AdminRecipeController {

    private final AdminRecipeService adminRecipeService;

    @GetMapping("/{recipeId}")
    public ResponseEntity<ApiResponse<AdminRecipeDetailResponse>> getRecipeDetail(@PathVariable Long recipeId) {
        return ResponseEntity.ok(ApiResponse.success("레시피 상세 조회 성공", adminRecipeService.getRecipeDetail(recipeId)));
    }
}
