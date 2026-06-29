package com.naengpa.naengpamasterbackend.shopping.controller;

import com.naengpa.naengpamasterbackend.global.response.ApiResponse;
import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemCreateRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemListResponse;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemResponse;
import com.naengpa.naengpamasterbackend.shopping.service.ShoppingItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-items")
public class ShoppingItemController {

    private final ShoppingItemService shoppingItemService;

    public ShoppingItemController(ShoppingItemService shoppingItemService) {
        this.shoppingItemService = shoppingItemService;
    }

    //장보기 등록
    @PostMapping
    public ResponseEntity<ApiResponse<ShoppingItemResponse>> createShoppingItem(
            Authentication authentication,
            @Valid @RequestBody ShoppingItemCreateRequest request
            ) {
        ShoppingItemResponse response = shoppingItemService.createShoppingItem(authentication.getName(), request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("장보기 재료가 등록되었습니다.", response));

    }

    //장보기 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<ShoppingItemListResponse>>> findShoppingItems(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(shoppingItemService.findShoppingItems(authentication.getName()))
        );
    }

    //장보기 항목 삭제
    @DeleteMapping("/{shoppingItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteShoppingItem(
            Authentication authentication,
            @Valid @PathVariable Long shoppingItemId
    ) {
        shoppingItemService.deleteShoppingItem(authentication.getName(), shoppingItemId);
        return ResponseEntity.ok(
                ApiResponse.success("장보기 항목이 삭제되었습니다.", null)
        );
    }
}
