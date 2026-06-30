package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.request.AdminProductCreateRequest;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminProductResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminProductRepository;
import com.naengpa.naengpamasterbackend.global.exception.DuplicateProductNameException;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;

    public AdminProductService(AdminProductRepository adminProductRepository) {
        this.adminProductRepository = adminProductRepository;
    }

    //어드민 사전 재료 전체 조회
    public List<AdminProductResponse> findAllProducts() {

        return adminProductRepository.findAllByOrderByProductIdAsc().stream()
                .map(AdminProductResponse::from)
                .toList();

    }

    //어드민 사전 재료 비활성 조회
    public List<AdminProductResponse> findInactiveProducts() {
        return adminProductRepository.findByIsActiveFalseOrderByProductIdAsc().stream()
                .map(AdminProductResponse::from)
                .toList();
    }

    //어드민 사전 재료 추가
    public AdminProductResponse createProduct(AdminProductCreateRequest request) {

        if (adminProductRepository.existsByName(request.name())) {
            throw new DuplicateProductNameException();
        }

        Product product = Product.create(
                request.productCategoryId(),
                request.name(),
                request.defaultExpiryDays()
        );

        Product savedProduct = adminProductRepository.save(product);

        return AdminProductResponse.from(savedProduct);
    }


}
