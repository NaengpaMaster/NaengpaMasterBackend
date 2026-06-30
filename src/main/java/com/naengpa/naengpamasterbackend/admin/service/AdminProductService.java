package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.request.AdminProductCreateRequest;
import com.naengpa.naengpamasterbackend.admin.dto.request.AdminProductUpdateRequest;
import com.naengpa.naengpamasterbackend.admin.dto.response.AdminProductResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminProductRepository;
import com.naengpa.naengpamasterbackend.global.exception.DuplicateProductNameException;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //어드민 사전 재료 수정
    @Transactional
    public AdminProductResponse updateProduct(Long productId, AdminProductUpdateRequest request) {

        Product product = adminProductRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (!product.getName().equals(request.name())
                && adminProductRepository.existsByName(request.name())) {
            throw new DuplicateProductNameException();
        }

        product.update(
                request.productCategoryId(),
                request.name(),
                request.defaultExpiryDays()
        );

        return AdminProductResponse.from(product);
    }


}
