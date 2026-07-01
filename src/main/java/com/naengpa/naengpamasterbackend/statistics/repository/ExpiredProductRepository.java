package com.naengpa.naengpamasterbackend.statistics.repository;

import com.naengpa.naengpamasterbackend.statistics.entity.ExpiredProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpiredProductRepository extends JpaRepository<ExpiredProduct, Long> {
}
