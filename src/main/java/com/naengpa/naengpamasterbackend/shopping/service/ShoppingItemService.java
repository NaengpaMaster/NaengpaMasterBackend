package com.naengpa.naengpamasterbackend.shopping.service;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import com.naengpa.naengpamasterbackend.product.service.ProductService;
import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemCreateRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemListResponse;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemResponse;
import com.naengpa.naengpamasterbackend.shopping.entity.ShoppingItem;
import com.naengpa.naengpamasterbackend.shopping.repository.ShoppingItemRepository;
import jakarta.validation.Valid;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingItemService {

    private final ShoppingItemRepository shoppingItemRepository;
    private final MemberRepository memberRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ShoppingItemService(
            ShoppingItemRepository shoppingItemRepository,
            MemberRepository memberRepository,
            ProductService productService,
            ProductRepository productRepository
            ) {
        this.shoppingItemRepository = shoppingItemRepository;
        this.memberRepository = memberRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    //회원 인증 공통 로직
    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("회원을 찾을 수 없습니다."));
    }

    //장보기 등록
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

    //장보기 조회
    public List<ShoppingItemListResponse> findShoppingItems(String email) {
        Member member = findMemberByEmail(email);


        List<ShoppingItem> shoppingItems = shoppingItemRepository.findByMemberIdAndIsDeletedFalse(
                member.getId());

        return toListResponse(shoppingItems);
    }

    //ShoppingItem + Product 정보를 합쳐서 화면용 응답으로 바꾸는 메서드
    private List<ShoppingItemListResponse> toListResponse(List<ShoppingItem> shoppingItems) {
        List<Long> productIds = shoppingItems.stream()
                .map(ShoppingItem::getProductId)
                .toList();

        List<Product> products = productRepository.findByProductIdInAndIsActiveTrue(productIds);

        return shoppingItems.stream()
                .map(shoppingItem -> {
                    Product product = products.stream()
                            .filter(p -> p.getProductId().equals(shoppingItem.getProductId()))
                            .findFirst()
                            .orElseThrow();

                    return new ShoppingItemListResponse(
                            shoppingItem.getShoppingItemId(),
                            shoppingItem.getProductId(),
                            product.getProductCategoryId(),
                            product.getName(),
                            shoppingItem.getQuantity(),
                            shoppingItem.getIsPurchased()
                    );
                })
                .toList();
    }
}

