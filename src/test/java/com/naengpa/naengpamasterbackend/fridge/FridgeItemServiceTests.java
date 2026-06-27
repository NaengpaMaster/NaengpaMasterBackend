package com.naengpa.naengpamasterbackend.fridge;

import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemCreateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemListResponse;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FridgeItemServiceTests {

    @Autowired
    private FridgeItemService fridgeItemService;

    @Test
    @DisplayName("냉장고 재료 등록 시 FridgeItemResponse 반환")
    void createFridgeItem_returnsFridgeItemResponse() {
        // given
        String email = "test-user@example.com";
        FridgeItemCreateRequest request = new FridgeItemCreateRequest(
                1L,
                "1개",
                LocalDate.now().plusDays(7),
                "테스트 등록"
        );

        // when
        FridgeItemResponse result = fridgeItemService.createFridgeItem(email, request);

        // then
        assertThat(result.fridgeItemId()).isNotNull();
        assertThat(result.productId()).isEqualTo(1L);
        assertThat(result.quantity()).isEqualTo("1개");
        assertThat(result.memo()).isEqualTo("테스트 등록");
    }

    @Test
    @DisplayName("냉장고 재료 목록 조회 시 삭제되지 않은 내 재료 목록을 반환")
    void findFridgeItemByProductId_returnsFridgeItemResponse() {
        //given
        String email = "test-user@example.com";

        //when
        List<FridgeItemListResponse> result = fridgeItemService.findFridgeItem(email);
        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).fridgeItemId()).isNotNull();
        assertThat(result.get(0).productId()).isNotNull();
        assertThat(result.get(0).productName()).isNotBlank();
        assertThat(result.get(0).productCategoryId()).isNotNull();
    }

    @Test
    @DisplayName("카테고리별 냉장고 재료 조회 시 해당 카테고리 재료만 반환")
    void findFridgeItemByCategories_returnsFridgeItemResponse() {
        //given
        String email = "test-user@example.com";
        Long categoryId = 1L;

        //when
        List<FridgeItemListResponse> result = fridgeItemService.findFridgeItemsByCategory(email, categoryId);

        //then
        assertThat(result).allMatch(item -> item.productCategoryId().equals(categoryId));
    }
}