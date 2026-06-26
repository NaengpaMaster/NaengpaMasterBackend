package com.naengpa.naengpamasterbackend.product;

import com.naengpa.naengpamasterbackend.product.dto.response.ProductSearchResponse;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.exception.ProductNotFoundException;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import com.naengpa.naengpamasterbackend.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void searchProducts_returnsProductSearchResponsesByPartialKeyword() {
        // given
        Product product = product(1L, 7L, "연두부", null);
        given(productRepository.findByIsActiveTrueAndNameContaining("두"))
                .willReturn(List.of(product));

        // when
        List<ProductSearchResponse> result = productService.searchProducts("두");

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).productId()).isEqualTo(1L);
        assertThat(result.get(0).name()).isEqualTo("연두부");
    }

    @Test
    void searchProducts_returnsEmptyListWhenNoProductMatches() {
        // given
        given(productRepository.findByIsActiveTrueAndNameContaining("없는재료"))
                .willReturn(List.of());

        // when
        List<ProductSearchResponse> result = productService.searchProducts("없는재료");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void validateExists_throwsExceptionWhenProductDoesNotExist() {
        // given
        given(productRepository.existsByProductIdAndIsActiveTrue(999L))
                .willReturn(false);

        // when & then
        assertThatThrownBy(() -> productService.validateExists(999L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    private Product product(Long productId, Long productCategoryId, String name, Integer defaultExpiryDays) {
        Product product = mock(Product.class);
        given(product.getProductId()).willReturn(productId);
        given(product.getProductCategoryId()).willReturn(productCategoryId);
        given(product.getName()).willReturn(name);
        given(product.getDefaultExpiryDays()).willReturn(defaultExpiryDays);
        return product;
    }
}
