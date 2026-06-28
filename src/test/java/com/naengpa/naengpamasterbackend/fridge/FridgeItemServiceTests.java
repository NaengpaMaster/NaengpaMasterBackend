package com.naengpa.naengpamasterbackend.fridge;

import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemCreateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemUpdateRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.request.FridgeItemUsePartialRequest;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemListResponse;
import com.naengpa.naengpamasterbackend.fridge.dto.response.FridgeItemResponse;
import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import com.naengpa.naengpamasterbackend.fridge.repository.FridgeItemRepository;
import com.naengpa.naengpamasterbackend.fridge.service.FridgeItemService;
import com.naengpa.naengpamasterbackend.member.entity.Member;
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
    @Autowired
    private FridgeItemRepository fridgeItemRepository;

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

    @Test
    @DisplayName("냉장고 재료 수정 시 수정된 FridgeItemResponse 반환")
    void updateFridgeItem_returnsUpdatedFridgeItemResponse() {
        // given
        String email = "test-user@example.com";

        FridgeItemCreateRequest createRequest = new FridgeItemCreateRequest(
                1L,
                "1개",
                LocalDate.now().plusDays(7),
                "수정 전"
        );

        FridgeItemResponse created = fridgeItemService.createFridgeItem(email, createRequest);

        FridgeItemUpdateRequest updateRequest = new FridgeItemUpdateRequest(
                1L,
                "2개",
                LocalDate.now().plusDays(10),
                "수정 후"
        );

        // when
        FridgeItemResponse result = fridgeItemService.updateFridgeItem(
                email,
                created.fridgeItemId(),
                updateRequest
        );

        // then
        assertThat(result.fridgeItemId()).isEqualTo(created.fridgeItemId());
        assertThat(result.productId()).isEqualTo(1L);
        assertThat(result.quantity()).isEqualTo("2개");
        assertThat(result.memo()).isEqualTo("수정 후");
    }

    @Test
    @DisplayName("냉장고 재료 삭제시 ???")
    void deleteFridgeItem_returnDeletedFridgeItemResponse() {
        //given
        String email = "test-user@example.com";
        FridgeItemCreateRequest request = new FridgeItemCreateRequest(
                1L,
                "1개",
                LocalDate.now().plusDays(7),
                "삭제 테스트"
        );

        FridgeItemResponse created = fridgeItemService.createFridgeItem(email, request);

        //when
        fridgeItemService.deleteFridgeItem(email, created.fridgeItemId());

        //then
        List<FridgeItemListResponse> result = fridgeItemService.findFridgeItem(email);

        assertThat(result)
                .extracting(FridgeItemListResponse::fridgeItemId)
                .doesNotContain(created.fridgeItemId());
    }

    @Test
    @DisplayName("냉장고 재료 전부 사용 처리 시 목록 조회에서 제외된다")
    void useAllFridgeItem_excludesUsedItemFromList() {
        // given
        String email = "test-user@example.com";

        FridgeItemCreateRequest request = new FridgeItemCreateRequest(
                1L,
                "1개",
                LocalDate.now().plusDays(7),
                "전부 사용 테스트"
        );

        FridgeItemResponse created = fridgeItemService.createFridgeItem(email, request);

        // when
        fridgeItemService.useAllFridgeItem(email, created.fridgeItemId());

        // then
        List<FridgeItemListResponse> result = fridgeItemService.findFridgeItem(email);

        assertThat(result)
                .extracting(FridgeItemListResponse::fridgeItemId)
                .doesNotContain(created.fridgeItemId());
    }

    @Test
    @DisplayName("냉장고 재료 일부 사용 처리 시 남은 수량으로 변경된다")
    void usePartialFridgeItem_updatesQuantity() {
        // given
        String email = "test-user@example.com";

        FridgeItemCreateRequest createRequest = new FridgeItemCreateRequest(
                1L,
                "3개",
                LocalDate.now().plusDays(7),
                "일부 사용 테스트"
        );

        FridgeItemResponse created = fridgeItemService.createFridgeItem(email, createRequest);

        FridgeItemUsePartialRequest request = new FridgeItemUsePartialRequest("2개");

        // when
        FridgeItemResponse result = fridgeItemService.usePartialFridgeItem(
                email,
                created.fridgeItemId(),
                request
        );

        // then
        assertThat(result.fridgeItemId()).isEqualTo(created.fridgeItemId());
        assertThat(result.quantity()).isEqualTo("2개");
    }

    @Test
    @DisplayName("유통기한 임박 재료 조회 시 오늘부터 3일 이내 재료를 반환")
    void findExpiringSoonFridgeItems_returnsExpiringSoonItems() {
        // given
        String email = "test-user@example.com";

        FridgeItemCreateRequest request = new FridgeItemCreateRequest(
                1L,
                "1개",
                LocalDate.now().plusDays(2),
                "임박 테스트"
        );

        FridgeItemResponse created = fridgeItemService.createFridgeItem(email, request);

        // when
        List<FridgeItemListResponse> result = fridgeItemService.findExpiringSoonFridgeItems(email);

        // then
        assertThat(result)
                .extracting(FridgeItemListResponse::fridgeItemId)
                .contains(created.fridgeItemId());
    }

    @Test
    @DisplayName("만료 재료 조회 시 오늘 이전 유통기한 재료를 반환")
    void findExpiredFridgeItems_returnsExpiredItems() {
        // given
        String email = "test-user@example.com";

        FridgeItemCreateRequest request = new FridgeItemCreateRequest(
                1L,
                "1개",
                LocalDate.now().minusDays(1),
                "만료 테스트"
        );

        FridgeItemResponse created = fridgeItemService.createFridgeItem(email, request);

        // when
        List<FridgeItemListResponse> result = fridgeItemService.findExpiredFridgeItems(email);

        // then
        assertThat(result)
                .extracting(FridgeItemListResponse::fridgeItemId)
                .contains(created.fridgeItemId());
    }
}