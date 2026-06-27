package com.naengpa.naengpamasterbackend.product;

import com.naengpa.naengpamasterbackend.product.dto.response.ProductSearchResponse;
import com.naengpa.naengpamasterbackend.product.exception.ProductNotFoundException;
import com.naengpa.naengpamasterbackend.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("부분 검색하면 ProductSearchResponse 반환")
    void searchProducts_returnsProductSearchResponsesByPartialKeyword() {
        // given
        String keyword = "두";

        // when
        List<ProductSearchResponse> result = productService.searchProducts(keyword);

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("검색 결과 없으면 빈 리스트 반환")
    void searchProducts_returnProductSearchResponsesByNotFound() {
        //given
        String keyword = "뷁";
        //when
        List<ProductSearchResponse> result = productService.searchProducts(keyword);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("없는 productId면 ProductNotFoundException 발생")
    void searchProducts_returnsProductNotFoundException() {
        // given
        Long productId = 999999999L;

        // when
        Throwable thrown = catchThrowable(() -> productService.validateExists(productId));

        // then
        assertThat(thrown).isInstanceOf(ProductNotFoundException.class);
    }
}