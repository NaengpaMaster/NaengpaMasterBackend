package com.naengpa.naengpamasterbackend.shopping;

import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemCheckRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemCreateRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.request.ShoppingItemMoveToFridgeRequest;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemListResponse;
import com.naengpa.naengpamasterbackend.shopping.dto.response.ShoppingItemResponse;
import com.naengpa.naengpamasterbackend.shopping.service.ShoppingItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    @DisplayName("장보기 목록 조회 시 내가 등록한 장보기 항목을 반환")
    void findShoppingItems_returnsMyShoppingItems() {
        // given
        String email = "test-user@example.com";

        ShoppingItemCreateRequest request = new ShoppingItemCreateRequest(
                1L,
                "1개"
        );

        ShoppingItemResponse created = shoppingItemService.createShoppingItem(email, request);

        // when
        List<ShoppingItemListResponse> result = shoppingItemService.findShoppingItems(email);

        // then
        assertThat(result)
                .extracting(ShoppingItemListResponse::shoppingItemId)
                .contains(created.shoppingItemId());
    }

    @Test
    @DisplayName("장보기 항목 삭제 시 목록 조회에서 제외")
    void deleteShoppingItem_excludesDeletedItemFromList() {
        // given
        String email = "test-user@example.com";
        ShoppingItemCreateRequest request = new ShoppingItemCreateRequest(
                1L,
                "1개"
        );

        ShoppingItemResponse created = shoppingItemService.createShoppingItem(email, request);

        // when
        shoppingItemService.deleteShoppingItem(email, created.shoppingItemId());

        // then
        List<ShoppingItemListResponse> result = shoppingItemService.findShoppingItems(email);

        assertThat(result)
                .extracting(ShoppingItemListResponse::shoppingItemId)
                .doesNotContain(created.shoppingItemId());
    }

    @Test
    @DisplayName("장보기 항목 체크 시 구매 여부가 true로 변경")
    void updateShoppingItemPurchased_setsIsPurchasedTrue() {
        // given
        String email = "test-user@example.com";
        ShoppingItemCreateRequest createRequest = new ShoppingItemCreateRequest(
                1L,
                "1개"
        );

        ShoppingItemResponse created = shoppingItemService.createShoppingItem(email, createRequest);
        ShoppingItemCheckRequest request = new ShoppingItemCheckRequest(true);

        // when
        ShoppingItemResponse result = shoppingItemService.updateShoppingItemPurchased(
                email,
                created.shoppingItemId(),
                request
        );

        // then
        assertThat(result.isPurchased()).isTrue();
    }

    @Test
    @DisplayName("장보기 항목 냉장고 반영 시 냉장고 재료를 생성하고 장보기 목록에서 제외")
    void moveShoppingItemToFridge_createsFridgeItemAndRemovesShoppingItem() {
        // given
        String email = "test-user@example.com";

        ShoppingItemCreateRequest createRequest = new ShoppingItemCreateRequest(
                1L,
                "1개"
        );

        ShoppingItemResponse created = shoppingItemService.createShoppingItem(email, createRequest);

        ShoppingItemMoveToFridgeRequest request = new ShoppingItemMoveToFridgeRequest(
                LocalDate.now().plusDays(7),
                "장보기에서 냉장고로 추가"
        );

        // when
        FridgeItemResponse result = shoppingItemService.moveShoppingItemToFridge(
                email,
                created.shoppingItemId(),
                request
        );

        // then
        assertThat(result.fridgeItemId()).isNotNull();
        assertThat(result.productId()).isEqualTo(created.productId());
        assertThat(result.quantity()).isEqualTo(created.quantity());
        assertThat(result.expiryDate()).isEqualTo(request.expiryDate());
        assertThat(result.memo()).isEqualTo(request.memo());

        List<ShoppingItemListResponse> shoppingItems = shoppingItemService.findShoppingItems(email);

        assertThat(shoppingItems)
                .extracting(ShoppingItemListResponse::shoppingItemId)
                .doesNotContain(created.shoppingItemId());
    }
}