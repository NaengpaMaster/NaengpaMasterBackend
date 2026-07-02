package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeCategoryResponse;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeCategoryService {

    private final RecipeCategoryRepository recipeCategoryRepository;

    public List<RecipeCategoryResponse> getCategories() {
        return recipeCategoryRepository.findAll().stream()
                .map(RecipeCategoryResponse::from)
                .toList();
    }
}
