package com.naengpa.naengpamasterbackend.fridge.controller;

import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemCreateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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
}
