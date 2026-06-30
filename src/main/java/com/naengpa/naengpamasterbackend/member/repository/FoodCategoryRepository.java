package com.naengpa.naengpamasterbackend.member.repository;

import com.naengpa.naengpamasterbackend.member.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

    List<FoodCategory> findByNameIn(Collection<String> names);
}
