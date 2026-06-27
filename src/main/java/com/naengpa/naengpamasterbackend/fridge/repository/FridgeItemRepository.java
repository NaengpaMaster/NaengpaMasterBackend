package com.naengpa.naengpamasterbackend.fridge.repository;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FridgeItemRepository extends JpaRepository<FridgeItem, Long> {

    List<FridgeItem> findByMemberIdAndIsDeletedFalse(Long memberId);

    List<FridgeItem> findByMemberIdAndProductIdInAndIsDeletedFalse(Long memberId, List<Long> productIds);

    Optional<FridgeItem> findByFridgeItemIdAndMemberIdAndIsDeletedFalse(Long fridgeItemId, Long memberId);

}
