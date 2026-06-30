package com.naengpa.naengpamasterbackend.admin;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminProductResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AdminProductServiceTests {

    @Autowired
    private AdminProductService adminProductService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("전체 사전 재료 조회 시 활성/비활성 재료를 모두 반환")
    void findAllProducts_returnsActiveAndInactiveProducts() {
        // given
        String activeName = "테스트활성재료" + System.nanoTime();
        String inactiveName = "테스트비활성재료" + System.nanoTime();

        jdbcTemplate.update("""
                INSERT INTO products (product_category_id, name, default_expiry_days, is_active)
                VALUES (1, ?, 7, true)
                """, activeName);

        jdbcTemplate.update("""
                INSERT INTO products (product_category_id, name, default_expiry_days, is_active)
                VALUES (1, ?, 7, false)
                """, inactiveName);

        // when
        List<AdminProductResponse> result = adminProductService.findAllProducts();

        // then
        assertThat(result)
                .extracting(AdminProductResponse::name)
                .contains(activeName, inactiveName);
    }

    @Test
    @DisplayName("비활성 사전 재료 조회 시 비활성 재료만 반환")
    void findInactiveProducts_returnsOnlyInactiveProducts() {
        // given
        String activeName = "테스트활성재료" + System.nanoTime();
        String inactiveName = "테스트비활성재료" + System.nanoTime();

        jdbcTemplate.update("""
                INSERT INTO products (product_category_id, name, default_expiry_days, is_active)
                VALUES (1, ?, 7, true)
                """, activeName);

        jdbcTemplate.update("""
                INSERT INTO products (product_category_id, name, default_expiry_days, is_active)
                VALUES (1, ?, 7, false)
                """, inactiveName);

        // when
        List<AdminProductResponse> result = adminProductService.findInactiveProducts();

        // then
        assertThat(result)
                .extracting(AdminProductResponse::name)
                .contains(inactiveName)
                .doesNotContain(activeName);

        assertThat(result)
                .extracting(AdminProductResponse::isActive)
                .containsOnly(false);
    }
}