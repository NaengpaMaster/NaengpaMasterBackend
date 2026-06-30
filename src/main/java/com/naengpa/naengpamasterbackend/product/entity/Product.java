package com.naengpa.naengpamasterbackend.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_category_id", nullable = false)
    private Long productCategoryId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "default_expiry_days")
    private Integer defaultExpiryDays;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 관리자 사전 재료 추가
    public static Product create(
            Long productCategoryId,
            String name,
            Integer defaultExpiryDays
    ) {
        Product product = new Product();
        product.productCategoryId = productCategoryId;
        product.name = name;
        product.defaultExpiryDays = defaultExpiryDays;
        product.isActive = true;
        product.createdAt = LocalDateTime.now();
        return product;
    }

    //관리자 사전 재료 수정
    public void update(
            Long productCategoryId,
            String name,
            Integer defaultExpiryDays
    ){
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.defaultExpiryDays = defaultExpiryDays;
        this.updatedAt = LocalDateTime.now();
    }
}

