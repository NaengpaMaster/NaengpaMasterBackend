package com.naengpa.naengpamasterbackend.recipe.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeDetailResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeCreateRequest;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeCreateResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeListResponse;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeCommandService;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 0d6e9c6 (feat: 레시피 등록 API 구현 (#83))
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeCommandService recipeCommandService;

    @GetMapping
    public ResponseEntity<ApiResponse<RecipeListResponse>> getRecipes(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.success(recipeService.getRecipes(pageable)));
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<ApiResponse<RecipeDetailResponse>> getRecipeDetail(
            @PathVariable Long recipeId,
            Authentication authentication
    ) {
        String email = authentication == null ? null : authentication.getName();
        return ResponseEntity.ok(
                ApiResponse.success("레시피 상세 조회 성공", recipeService.getRecipeDetail(recipeId, email))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecipeCreateResponse>> createRecipe(
            @Valid @RequestBody RecipeCreateRequest request,
            Authentication authentication
    ) {
        RecipeCreateResponse created = recipeCommandService.createRecipe(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("레시피가 등록되었습니다.", created));
    }

}
