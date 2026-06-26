package com.naengpa.naengpamasterbackend.product;

import com.naengpa.naengpamasterbackend.product.dto.response.ProductCategoryResponse;
import com.naengpa.naengpamasterbackend.product.entity.ProductCategory;
import com.naengpa.naengpamasterbackend.product.service.ProductCategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductCategoryServiceTests {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    @DisplayName("카테고리 목록 조회 시 ProductCategoryResponse 리스트 반환")
    void productCategoryResponse(){
        //given
        //when
        List<ProductCategoryResponse> result = productCategoryService.findCategories();

        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).productCategoryId()).isNotNull();
        assertThat(result.get(0).name()).isNotBlank();

    }


}
