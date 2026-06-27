package com.naengpa.naengpamasterbackend.product.repository;

import com.naengpa.naengpamasterbackend.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsActiveTrueAndNameContaining(String keyword);

    boolean existsByProductIdAndIsActiveTrue(Long productId);
}