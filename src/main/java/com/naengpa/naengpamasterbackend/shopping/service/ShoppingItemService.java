package com.naengpa.naengpamasterbackend.shopping.service;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.product.service.ProductService;
import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemCreateRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemResponse;
import com.naengpa.naengpamasterbackend.shopping.entity.ShoppingItem;
import com.naengpa.naengpamasterbackend.shopping.repository.ShoppingItemRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingItemService {

    private final ShoppingItemRepository shoppingItemRepository;
    private final MemberRepository memberRepository;
    private final ProductService productService;

    public ShoppingItemService(
            ShoppingItemRepository shoppingItemRepository,
            MemberRepository memberRepository,
            ProductService productService
            ) {
        this.shoppingItemRepository = shoppingItemRepository;
        this.memberRepository = memberRepository;
        this.productService = productService;
    }

    //회원 인증 공통 로직
    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("회원을 찾을 수 없습니다."));
    }

    public ShoppingItemResponse createShoppingItem(String email , @Valid ShoppingItemCreateRequest request) {
        Member member = findMemberByEmail(email);

        //존재하는 사전 재료인지
        productService.validateExists(request.productId());

        ShoppingItem shoppingItem = ShoppingItem.create(
                member.getId(),
                request.productId(),
                request.quantity()
        );

        return ShoppingItemResponse.from(shoppingItemRepository.save(shoppingItem));
    }
}

