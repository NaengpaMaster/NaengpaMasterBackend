package com.naengpa.naengpamasterbackend.fridge.controller;

import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemCreateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemUpdateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemUsePartialRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemListResponse;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/fridge-items")
public class FridgeItemController {

    private final FridgeItemService fridgeItemService;

    public FridgeItemController(FridgeItemService fridgeItemService) {
        this.fridgeItemService = fridgeItemService;
    }

    //재료 등록
    @PostMapping
    public ResponseEntity<ApiResponse<FridgeItemResponse>> createFridgeItem(
            Authentication authentication,
            @Valid @RequestBody FridgeItemCreateRequest request) {
        FridgeItemResponse response = fridgeItemService.createFridgeItem(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("냉장고 재료가 등록되었습니다.", response));
    }

    //냉장고 재료 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<FridgeItemListResponse>>> findFridgeItems(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.success(fridgeItemService.findFridgeItem(authentication.getName())));
    }

    //냉장고 카테고리별 조회
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<ApiResponse<List<FridgeItemListResponse>>> findFridgeItemsByCategory(
            Authentication authentication,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(fridgeItemService.findFridgeItemsByCategory(authentication.getName(), categoryId))
        );
    }

    //냉장고 재료 수정
    @PatchMapping("/{fridgeItemId}")
    public ResponseEntity<ApiResponse<FridgeItemResponse>> updateFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId,
            @Valid @RequestBody FridgeItemUpdateRequest request
    ) {
        FridgeItemResponse response = fridgeItemService.updateFridgeItem(authentication.getName(), fridgeItemId, request);
        return ResponseEntity.ok(ApiResponse.success("냉장고 재료가 수정되었습니다.", response));
    }

    //냉장고 재료 삭제
    @DeleteMapping("/{fridgeItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId
    ) {
        fridgeItemService.deleteFridgeItem(authentication.getName(), fridgeItemId);
        return ResponseEntity.ok(ApiResponse.success("냉장고 재료가 삭제되었습니다.", null));
    }

    //냉장고 재료 전부 사용
    @PatchMapping("/{fridgeItemId}/use-all")
    public ResponseEntity<ApiResponse<Void>> useAllFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId
    ) {
        fridgeItemService.useAllFridgeItem(authentication.getName(), fridgeItemId);
        return ResponseEntity.ok(ApiResponse.success("냉장고 재료를 전부 사용 처리했습니다.", null));
    }

    //냉장고 재료 일부 사용
    @PatchMapping("/{fridgeItemId}/use-partial")
    public ResponseEntity<ApiResponse<FridgeItemResponse>> usePartialFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId,
            @Valid @RequestBody FridgeItemUsePartialRequest request
    ) {
        FridgeItemResponse response = fridgeItemService.usePartialFridgeItem(
                authentication.getName(),
                fridgeItemId,
                request
        );
        return ResponseEntity.ok(ApiResponse.success("냉장고 재료를 일부 사용 처리했습니다.", response));
    }

    //유통기한 임박 재료 조회
    @GetMapping("/expiring-soon")
    public ResponseEntity<ApiResponse<List<FridgeItemListResponse>>> findExpiringSoonFridgeItems(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.success(fridgeItemService.findExpiringSoonFridgeItems(authentication.getName())));
    }

    //만료 재료 조회
    @GetMapping("/expired")
    public ResponseEntity<ApiResponse<List<FridgeItemListResponse>>> findExpiredFridgeItems(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.success(fridgeItemService.findExpiredFridgeItems(authentication.getName())));
    }
}
