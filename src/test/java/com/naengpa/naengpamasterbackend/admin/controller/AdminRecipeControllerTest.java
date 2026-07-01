package com.naengpa.naengpamasterbackend.admin.controller;

import com.naengpa.naengpamasterbackend.admin.dto.response.AdminRecipeDetailResponse;
import com.naengpa.naengpamasterbackend.admin.service.AdminRecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminRecipeController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminRecipeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AdminRecipeService adminRecipeService;

    @Test
    @DisplayName("관리자 레시피 상세 조회 - 200과 상세 정보를 반환한다")
    void getRecipeDetail_returns200() throws Exception {
        AdminRecipeDetailResponse response = new AdminRecipeDetailResponse(
                101L, "된장찌개", "구수한 된장찌개", "찌개", 20, "EASY",
                List.of(
                        new AdminRecipeDetailResponse.Ingredient(1L, "두부"),
                        new AdminRecipeDetailResponse.Ingredient(2L, "된장")
                ),
                List.of(
                        new AdminRecipeDetailResponse.Step(1, "육수를 끓인다"),
                        new AdminRecipeDetailResponse.Step(2, "된장을 푼다"),
                        new AdminRecipeDetailResponse.Step(3, "두부를 넣는다"),
                        new AdminRecipeDetailResponse.Step(4, "대파를 넣는다")
                )
        );
        given(adminRecipeService.getRecipeDetail(eq(101L))).willReturn(response);

        mockMvc.perform(get("/api/v1/admin/recipes/{recipeId}", 101L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("레시피 상세 조회 성공"))
                .andExpect(jsonPath("$.data.recipeId").value(101))
                .andExpect(jsonPath("$.data.recipeName").value("된장찌개"))
                .andExpect(jsonPath("$.data.description").value("구수한 된장찌개"))
                .andExpect(jsonPath("$.data.category").value("찌개"))
                .andExpect(jsonPath("$.data.cookTime").value(20))
                .andExpect(jsonPath("$.data.difficulty").value("EASY"))
                .andExpect(jsonPath("$.data.ingredients.length()").value(2))
                .andExpect(jsonPath("$.data.ingredients[0].ingredientId").value(1))
                .andExpect(jsonPath("$.data.ingredients[0].ingredientName").value("두부"))
                .andExpect(jsonPath("$.data.steps.length()").value(4))
                .andExpect(jsonPath("$.data.steps[0].stepOrder").value(1))
                .andExpect(jsonPath("$.data.steps[3].stepOrder").value(4))
                .andExpect(jsonPath("$.data.steps[3].content").value("대파를 넣는다"));
    }

    @Test
    @DisplayName("관리자 레시피 상세 조회 - 존재하지 않거나 삭제된 레시피면 404를 반환한다")
    void getRecipeDetail_returns404_whenNotFound() throws Exception {
        given(adminRecipeService.getRecipeDetail(eq(999L)))
                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다."));

        mockMvc.perform(get("/api/v1/admin/recipes/{recipeId}", 999L))
                .andExpect(status().isNotFound());
    }
}
