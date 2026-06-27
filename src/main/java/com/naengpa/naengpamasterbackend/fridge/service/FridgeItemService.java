package com.naengpa.naengpamasterbackend.fridge.service;

import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemCreateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import com.naengpa.naengpamasterbackend.fridge.repository.FridgeItemRepository;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.product.service.ProductService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FridgeItemService {

    private final FridgeItemRepository fridgeItemRepository;
    private final MemberRepository memberRepository;
    private final ProductService productService;

    public FridgeItemService(
            FridgeItemRepository fridgeItemRepository,
            MemberRepository memberRepository,
            ProductService productService
    ) {
        this.fridgeItemRepository = fridgeItemRepository;
        this.memberRepository = memberRepository;
        this.productService = productService;
    }

    // 냉장고 재료 등록
    @Transactional
    public FridgeItemResponse createFridgeItem(String email, FridgeItemCreateRequest request) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("회원을 찾을 수 없습니다."));

        productService.validateExists(request.productId());

        FridgeItem fridgeItem = FridgeItem.create(
                member.getId(),
                request.productId(),
                request.quantity(),
                request.expiryDate(),
                request.memo()
        );

        return FridgeItemResponse.from(fridgeItemRepository.save(fridgeItem));
    }
}