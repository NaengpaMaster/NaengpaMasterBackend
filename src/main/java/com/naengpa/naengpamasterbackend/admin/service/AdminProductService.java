package com.naengpa.naengpamasterbackend.admin.service;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminProductResponse;
import com.naengpa.naengpamasterbackend.admin.repository.AdminProductRepository;
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



}
