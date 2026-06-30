package com.naengpa.naengpamasterbackend.recipe.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeDetailResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeCreateRequest;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeUpdateRequest;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeCreateResponse;
import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeLikeResponse;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeCommandService;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeCommandService recipeCommandService;

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

    @PatchMapping("/{recipeId}")
    public ResponseEntity<ApiResponse<Void>> updateRecipe(
            @PathVariable Long recipeId,
            @Valid @RequestBody RecipeUpdateRequest request,
            Authentication authentication
    ) {
        recipeCommandService.updateRecipe(recipeId, authentication.getName(), isAdmin(authentication), request);
        return ResponseEntity.ok(ApiResponse.success("레시피가 수정되었습니다.", null));
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<ApiResponse<Void>> deleteRecipe(@PathVariable Long recipeId,
                                                          Authentication authentication) {
        recipeCommandService.deleteRecipe(recipeId, authentication.getName(), isAdmin(authentication));
        return ResponseEntity.ok(ApiResponse.success("레시피가 삭제되었습니다.", null));
    }

    @PostMapping("/{recipeId}/like")
    public ResponseEntity<ApiResponse<RecipeLikeResponse>> toggleLike(
            @PathVariable Long recipeId,
            Authentication authentication
    ) {
        RecipeLikeResponse response = recipeCommandService.toggleLike(authentication.getName(), recipeId);
        String message = response.liked() ? "좋아요 등록 성공" : "좋아요 취소 성공";
        return ResponseEntity.ok(ApiResponse.success(message, response));
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ADMIN"::equals);
    }
}
