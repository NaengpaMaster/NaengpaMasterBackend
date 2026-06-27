package com.naengpa.naengpamasterbackend.fridge.repository;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridgeItemRepository extends JpaRepository<FridgeItem, Long> {

}
