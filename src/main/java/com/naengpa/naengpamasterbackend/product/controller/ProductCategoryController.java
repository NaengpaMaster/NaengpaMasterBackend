package com.naengpa.naengpamasterbackend.product.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.product.dto.response.ProductCategoryResponse;
import com.naengpa.naengpamasterbackend.product.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductCategoryResponse>>> findCategories() {
        return ResponseEntity.ok(ApiResponse.success(productCategoryService.findCategories()));
    }


}
