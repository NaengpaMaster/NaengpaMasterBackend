package com.naengpa.naengpamasterbackend.fridge.controller;

import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemCreateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemUpdateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemUsePartialRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemListResponse;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public FridgeItemResponse createFridgeItem(
            Authentication authentication,
            @Valid @RequestBody FridgeItemCreateRequest request) {
        return fridgeItemService.createFridgeItem(authentication.getName(), request);
    }

    //냉장고 재료 조회
    @GetMapping
    public List<FridgeItemListResponse> findFridgeItems(Authentication authentication) {
        return fridgeItemService.findFridgeItem(authentication.getName());
    }

    //냉장고 카테고리별 조회
    @GetMapping("/categories/{categoryId}")
    public List<FridgeItemListResponse> findFridgeItemsByCategory(
            Authentication authentication,
            @PathVariable Long categoryId
    ) {
        return fridgeItemService.findFridgeItemsByCategory(authentication.getName(), categoryId);
    }

    //냉장고 재료 수정
    @PatchMapping("/{fridgeItemId}")
    public FridgeItemResponse updateFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId,
            @Valid @RequestBody FridgeItemUpdateRequest request
    ) {
        return fridgeItemService.updateFridgeItem(authentication.getName(), fridgeItemId, request);
    }

    //냉장고 재료 삭제
    @DeleteMapping("/{fridgeItemId}")
    public void deleteFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId
    ) {
        fridgeItemService.deleteFridgeItem(authentication.getName(), fridgeItemId);
    }

    //냉장고 재료 전부 사용
    @PatchMapping("/{fridgeItemId}/use-all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void useAllFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId
    ) {
        fridgeItemService.useAllFridgeItem(authentication.getName(), fridgeItemId);
    }

    //냉장고 재료 일부 사용
    @PatchMapping("/{fridgeItemId}/use-partial")
    public FridgeItemResponse usePartialFridgeItem(
            Authentication authentication,
            @PathVariable Long fridgeItemId,
            @Valid @RequestBody FridgeItemUsePartialRequest request
    ) {
        return fridgeItemService.usePartialFridgeItem(
                authentication.getName(),
                fridgeItemId,
                request
        );
    }

    //유통기한 임박 재료 조회
    @GetMapping("/expiring-soon")
    public List<FridgeItemListResponse> findExpiringSoonFridgeItems(Authentication authentication) {
        return fridgeItemService.findExpiringSoonFridgeItems(authentication.getName());
    }

    //만료 재료 조회
    @GetMapping("/expired")
    public List<FridgeItemListResponse> findExpiredFridgeItems(Authentication authentication) {
        return fridgeItemService.findExpiredFridgeItems(authentication.getName());
    }
}
