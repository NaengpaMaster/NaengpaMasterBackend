package com.naengpa.naengpamasterbackend.product.service;

import com.naengpa.naengpamasterbackend.product.dto.response.ProductSearchResponse;
import com.naengpa.naengpamasterbackend.product.exception.ProductNotFoundException;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 사전 재료 검색
    public List<ProductSearchResponse> searchProducts(String keyword) {
        return productRepository.findByIsActiveTrueAndNameContaining(keyword).stream()
                .map(ProductSearchResponse::from)
                .toList();
    }

    //존재하지 않는 재료 ID 저장 차단, DoD = Definition of Done 완료조건
    public void validateExists(Long productId) {
        if(!productRepository.existsByProductIdAndIsActiveTrue(productId)) {
            throw new ProductNotFoundException(productId);
        }
    }

}
