package com.naengpa.naengpamasterbackend.product.controller;

import com.naengpa.naengpamasterbackend.product.dto.response.ProductCategoryResponse;
import com.naengpa.naengpamasterbackend.product.service.ProductCategoryService;
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
    public List<ProductCategoryResponse> findCategories() {
        return productCategoryService.findCategories();
    }


}
