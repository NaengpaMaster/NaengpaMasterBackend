package com.naengpa.naengpamasterbackend.product.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.product.dto.response.ProductSearchResponse;
import com.naengpa.naengpamasterbackend.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //사전 재료 검색 API-200
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductSearchResponse>>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(productService.searchProducts(keyword)));
    }
}
