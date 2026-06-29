package com.naengpa.naengpamasterbackend.recipe;

import com.naengpa.naengpamasterbackend.global.security.JwtTokenProvider;
import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RecipeLikeApiTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private Long recipeId;
    private String token;

    @BeforeEach
    void setUp() {
        String unique = UUID.randomUUID().toString().substring(0, 8);

        Member member = memberRepository.save(Member.createUser(
                "recipe-like-" + unique + "@example.com",
                "encoded-password",
                "좋아요테스터" + unique,
                HouseholdType.ETC
        ));
        token = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());

        Long categoryId = nextId("recipe_categories", "recipe_category_id");
        jdbcTemplate.update(
                "INSERT INTO recipe_categories (recipe_category_id, name) VALUES (?, ?)",
                categoryId, "찌개-" + unique
        );

        recipeId = nextId("recipes", "recipe_id");
        jdbcTemplate.update(
                "INSERT INTO recipes (recipe_id, recipe_category_id, name, description, cooking_time, difficulty, is_deleted) "
                        + "VALUES (?, ?, ?, ?, ?, ?, false)",
                recipeId, categoryId, "된장찌개", "구수한 된장찌개", 20, "EASY"
        );
    }

    private Long nextId(String table, String idColumn) {
        return jdbcTemplate.queryForObject(
                "SELECT COALESCE(MAX(" + idColumn + "), 0) + 1 FROM " + table,
                Long.class
        );
    }

    @Test
    @DisplayName("좋아요 토글은 등록→해제→등록 순으로 동작하며 매번 200과 liked/likeCount를 즉시 반환한다")
    void toggleLike_registerThenCancelThenRegister() throws Exception {
        // 등록: liked=true, likeCount=1
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/like", recipeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("좋아요 등록 성공")))
                .andExpect(jsonPath("$.data.liked", is(true)))
                .andExpect(jsonPath("$.data.likeCount", is(1)));

        // 해제: liked=false, likeCount=0
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/like", recipeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("좋아요 취소 성공")))
                .andExpect(jsonPath("$.data.liked", is(false)))
                .andExpect(jsonPath("$.data.likeCount", is(0)));

        // 재등록: liked=true, likeCount=1
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/like", recipeId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.liked", is(true)))
                .andExpect(jsonPath("$.data.likeCount", is(1)));
    }

    @Test
    @DisplayName("인증 없이 좋아요 토글을 시도하면 401을 반환한다")
    void toggleLike_withoutAuth_returns401() throws Exception {
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/like", recipeId))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("존재하지 않는 recipeId로 좋아요 토글을 시도하면 404를 반환한다")
    void toggleLike_recipeNotFound_returns404() throws Exception {
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/like", 999_999_999L)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("레시피를 찾을 수 없습니다.")));
    }
}
