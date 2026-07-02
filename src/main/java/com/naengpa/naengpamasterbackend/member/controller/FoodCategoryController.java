package com.naengpa.naengpamasterbackend.member.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.member.dto.response.FoodCategoryResponse;
import com.naengpa.naengpamasterbackend.member.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food-categories")
@RequiredArgsConstructor
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FoodCategoryResponse>>> getCategories() {
        return ResponseEntity.ok(ApiResponse.success(foodCategoryService.getCategories()));
    }
}
