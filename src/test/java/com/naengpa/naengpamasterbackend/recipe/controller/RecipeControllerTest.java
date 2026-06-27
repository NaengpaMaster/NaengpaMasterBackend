package com.naengpa.naengpamasterbackend.recipe.controller;

import com.naengpa.naengpamasterbackend.recipe.dto.response.RecipeCreateResponse;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeCommandService;
import com.naengpa.naengpamasterbackend.recipe.service.RecipeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
@AutoConfigureMockMvc(addFilters = false)
class RecipeControllerTest {

    private static final Authentication AUTH =
            new UsernamePasswordAuthenticationToken("test@example.com", null);

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    RecipeService recipeService;

    @MockitoBean
    RecipeCommandService recipeCommandService;

    @Test
    @DisplayName("레시피 등록 - 201과 생성된 레시피 ID를 반환한다")
    void createRecipe_returns201() throws Exception {
        given(recipeCommandService.createRecipe(any(), any())).willReturn(new RecipeCreateResponse(10L));

        String body = """
                {"name":"김치찌개","description":"맛있다","cookingTime":30,
                 "difficulty":"EASY","categoryId":2,"productIds":[1,2],"steps":["끓인다"]}
                """;

        mockMvc.perform(post("/api/v1/recipes")
                        .principal(AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.recipeId").value(10));
    }

    @Test
    @DisplayName("레시피 등록 - 레시피명이 30자를 초과하면 400을 반환한다")
    void createRecipe_returns400_whenNameTooLong() throws Exception {
        String longName = "가".repeat(31);
        String body = """
                {"name":"%s","difficulty":"EASY","categoryId":2,"productIds":[1],"steps":["끓인다"]}
                """.formatted(longName);

        mockMvc.perform(post("/api/v1/recipes")
                        .principal(AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("레시피 등록 - 필수 재료가 없으면 400을 반환한다")
    void createRecipe_returns400_whenNoProducts() throws Exception {
        String body = """
                {"name":"김치찌개","difficulty":"EASY","categoryId":2,"productIds":[],"steps":["끓인다"]}
                """;

        mockMvc.perform(post("/api/v1/recipes")
                        .principal(AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("레시피 등록 - 조리 과정이 없으면 400을 반환한다")
    void createRecipe_returns400_whenNoSteps() throws Exception {
        String body = """
                {"name":"김치찌개","difficulty":"EASY","categoryId":2,"productIds":[1],"steps":[]}
                """;

        mockMvc.perform(post("/api/v1/recipes")
                        .principal(AUTH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }
}
