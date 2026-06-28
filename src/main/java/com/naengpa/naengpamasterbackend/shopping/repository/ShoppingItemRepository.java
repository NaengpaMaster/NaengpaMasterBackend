package com.naengpa.naengpamasterbackend.shopping.repository;

import com.naengpa.naengpamasterbackend.shopping.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
}
