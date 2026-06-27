package com.naengpa.naengpamasterbackend.product.service;

import com.naengpa.naengpamasterbackend.product.dto.response.ProductCategoryResponse;
import com.naengpa.naengpamasterbackend.product.entity.ProductCategory;
import com.naengpa.naengpamasterbackend.product.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;


    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    //카테고리 목록 조회
    public List<ProductCategoryResponse> findCategories() {
        return productCategoryRepository.findAll().stream()
                .map(ProductCategoryResponse::from)
                .toList();
    }

}
