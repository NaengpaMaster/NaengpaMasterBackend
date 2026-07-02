package com.naengpa.naengpamasterbackend.member.service;

import com.naengpa.naengpamasterbackend.member.dto.response.FoodCategoryResponse;
import com.naengpa.naengpamasterbackend.member.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public List<FoodCategoryResponse> getCategories() {
        return foodCategoryRepository.findAll().stream()
                .map(FoodCategoryResponse::from)
                .toList();
    }
}
