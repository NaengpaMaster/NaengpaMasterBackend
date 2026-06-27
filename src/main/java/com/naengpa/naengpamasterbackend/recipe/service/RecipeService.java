package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeListResponse;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeListResponse getRecipes(Pageable pageable) {
        return RecipeListResponse.from(recipeRepository.findRecipeList(pageable));
    }
}
