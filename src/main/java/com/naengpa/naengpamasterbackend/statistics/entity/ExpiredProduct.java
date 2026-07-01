package com.naengpa.naengpamasterbackend.statistics.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expired_products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expired_product_id")
    private Long expiredProductId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_category_id", nullable = false)
    private Long productCategoryId;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "category_name", length = 100)
    private String categoryName;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public static ExpiredProduct create(
            Long memberId,
            Long productId,
            Long productCategoryId,
            String productName,
            String categoryName
    ) {
        ExpiredProduct expiredProduct = new ExpiredProduct();
        expiredProduct.memberId = memberId;
        expiredProduct.productId = productId;
        expiredProduct.productCategoryId = productCategoryId;
        expiredProduct.productName = productName;
        expiredProduct.categoryName = categoryName;
        expiredProduct.createdAt = LocalDate.now();

        return expiredProduct;
    }
}
