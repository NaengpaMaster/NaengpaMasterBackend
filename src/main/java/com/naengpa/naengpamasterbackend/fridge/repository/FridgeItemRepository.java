package com.naengpa.naengpamasterbackend.fridge.repository;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FridgeItemRepository extends JpaRepository<FridgeItem, Long> {

    List<FridgeItem> findByMemberIdAndIsDeletedFalse(Long memberId);

    List<FridgeItem> findByMemberIdAndProductIdInAndIsDeletedFalse(Long memberId, List<Long> productIds);

}
