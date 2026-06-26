package com.naengpa.naengpamasterbackend.product.repository;

import com.naengpa.naengpamasterbackend.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
