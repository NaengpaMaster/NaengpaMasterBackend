package com.naengpa.naengpamasterbackend.recipe.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.response.AdminRecipeDetailResponse;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/recipes")
@RequiredArgsConstructor
public class RecipeAdminController {

    private final RecipeAdminService recipeAdminService;

    @GetMapping("/{recipeId}")
    public ResponseEntity<ApiResponse<AdminRecipeDetailResponse>> getRecipeDetail(@PathVariable Long recipeId) {
        return ResponseEntity.ok(ApiResponse.success("레시피 상세 조회 성공", recipeAdminService.getRecipeDetail(recipeId)));
    }
}
