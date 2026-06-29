package com.naengpa.naengpamasterbackend.recipe.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeAdminSecurityTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("미인증 요청 시 401을 반환한다")
    void returns401_whenUnauthenticated() throws Exception {
        mockMvc.perform(get("/api/v1/admin/recipes/{recipeId}", 1L))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("관리자 권한이 없으면 403을 반환한다")
    void returns403_whenNotAdmin() throws Exception {
        mockMvc.perform(get("/api/v1/admin/recipes/{recipeId}", 1L)
                        .with(user("user@test.com").authorities(new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isForbidden());
    }
}
