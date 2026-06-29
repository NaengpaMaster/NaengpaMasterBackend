package com.naengpa.naengpamasterbackend.shopping.repository;

import com.naengpa.naengpamasterbackend.shopping.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {

    List<ShoppingItem> findByMemberIdAndIsDeletedFalse(Long memberId);

}
