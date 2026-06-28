package com.naengpa.naengpamasterbackend.shopping;

import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemCreateRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemResponse;
import com.naengpa.naengpamasterbackend.shopping.service.ShoppingItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShoppingItemServiceTests {

    @Autowired
    private ShoppingItemService shoppingItemService;

    @Test
    @DisplayName("장보기 항목 추가 시 ShoppingItemResponse를 반환")
    void createShoppingItem_returnsShoppingItemResponse() {
        // given
        String email = "test-user@example.com";
        ShoppingItemCreateRequest request = new ShoppingItemCreateRequest(
                1L,
                "1개"
        );

        // when
        ShoppingItemResponse result = shoppingItemService.createShoppingItem(email, request);

        // then
        assertThat(result.shoppingItemId()).isNotNull();
        assertThat(result.memberId()).isNotNull();
        assertThat(result.productId()).isEqualTo(1L);
        assertThat(result.quantity()).isEqualTo("1개");
    }

    @Test
    @DisplayName("장보기 항목 추가 시 구매 여부는 false로 저장")
    void createShoppingItem_setsIsPurchasedFalse() {
        // given
        String email = "test-user@example.com";
        ShoppingItemCreateRequest request = new ShoppingItemCreateRequest(
                1L,
                "1개"
        );

        // when
        ShoppingItemResponse result = shoppingItemService.createShoppingItem(email, request);

        // then
        assertThat(result.isPurchased()).isFalse();
    }
}