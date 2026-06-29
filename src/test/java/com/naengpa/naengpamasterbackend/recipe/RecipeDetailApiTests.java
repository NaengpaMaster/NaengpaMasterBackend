package com.naengpa.naengpamasterbackend.recipe;

import com.naengpa.naengpamasterbackend.global.security.JwtTokenProvider;
import com.naengpa.naengpamasterbackend.member.entity.HouseholdType;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RecipeDetailApiTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private Long recipeId;
    private Long tofuId;     // 보유
    private Long doenjangId; // 미보유
    private Long greenOnionId; // 미보유
    private String tofuName;
    private String doenjangName;
    private String greenOnionName;
    private String token;

    @BeforeEach
    void setUp() {
        String unique = UUID.randomUUID().toString().substring(0, 8);

        // 회원 생성
        Member member = memberRepository.save(Member.createUser(
                "recipe-detail-" + unique + "@example.com",
                "encoded-password",
                "레시피상세테스터" + unique,
                HouseholdType.ETC
        ));
        token = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());

        // 카테고리 생성 (시퀀스 상태와 무관하도록 명시적 PK 사용)
        Long categoryId = nextId("recipe_categories", "recipe_category_id");
        jdbcTemplate.update(
                "INSERT INTO recipe_categories (recipe_category_id, name) VALUES (?, ?)",
                categoryId, "찌개-" + unique
        );

        // 레시피 생성
        recipeId = nextId("recipes", "recipe_id");
        jdbcTemplate.update(
                "INSERT INTO recipes (recipe_id, recipe_category_id, name, description, cooking_time, difficulty, is_deleted) "
                        + "VALUES (?, ?, ?, ?, ?, ?, false)",
                recipeId, categoryId, "된장찌개", "집에 있는 재료로 간편하게 끓이는 구수한 된장찌개", 20, "EASY"
        );

        // 재료(상품) 생성 (products.name 은 UNIQUE 이므로 고유 접미사 사용)
        tofuName = "두부-" + unique;
        doenjangName = "된장-" + unique;
        greenOnionName = "대파-" + unique;
        long productId = nextId("products", "product_id");
        tofuId = insertProduct(productId, tofuName);
        doenjangId = insertProduct(productId + 1, doenjangName);
        greenOnionId = insertProduct(productId + 2, greenOnionName);

        // 레시피 필수 재료 매핑 (순서 보장 위해 순차 삽입)
        long rrpId = nextId("recipe_required_products", "recipe_required_product_id");
        insertRequiredProduct(rrpId, recipeId, tofuId);
        insertRequiredProduct(rrpId + 1, recipeId, doenjangId);
        insertRequiredProduct(rrpId + 2, recipeId, greenOnionId);

        // 조리 과정 (순서 섞어서 삽입 -> stepNo 정렬 확인용)
        long stepId = nextId("recipe_steps", "recipe_step_id");
        insertStep(stepId, recipeId, 2, "된장 2큰술을 풀어줍니다.");
        insertStep(stepId + 1, recipeId, 1, "냄비에 멸치육수 2컵을 끓입니다.");
        insertStep(stepId + 2, recipeId, 3, "두부, 대파를 넣고 끓이면 완성.");

        // 좋아요 등록
        Long favoriteId = nextId("recipe_favorites", "recipe_favorite_id");
        jdbcTemplate.update(
                "INSERT INTO recipe_favorites (recipe_favorite_id, recipe_id, member_id) VALUES (?, ?, ?)",
                favoriteId, recipeId, member.getId()
        );

        // 냉장고에 두부만 보유
        Long fridgeItemId = nextId("fridge_items", "fridge_item_id");
        jdbcTemplate.update(
                "INSERT INTO fridge_items (fridge_item_id, member_id, product_id, quantity, is_deleted) "
                        + "VALUES (?, ?, ?, ?, false)",
                fridgeItemId, member.getId(), tofuId, "1모"
        );
    }

    private Long nextId(String table, String idColumn) {
        return jdbcTemplate.queryForObject(
                "SELECT COALESCE(MAX(" + idColumn + "), 0) + 1 FROM " + table,
                Long.class
        );
    }

    private Long insertProduct(long productId, String name) {
        jdbcTemplate.update(
                "INSERT INTO products (product_id, product_category_id, name, is_active, created_at) "
                        + "VALUES (?, 1, ?, true, CURRENT_TIMESTAMP)",
                productId, name
        );
        return productId;
    }

    private void insertRequiredProduct(long id, Long recipeId, Long productId) {
        jdbcTemplate.update(
                "INSERT INTO recipe_required_products (recipe_required_product_id, recipe_id, product_id) "
                        + "VALUES (?, ?, ?)",
                id, recipeId, productId
        );
    }

    private void insertStep(long id, Long recipeId, int stepNo, String content) {
        jdbcTemplate.update(
                "INSERT INTO recipe_steps (recipe_step_id, recipe_id, step_no, content, is_deleted) "
                        + "VALUES (?, ?, ?, ?, false)",
                id, recipeId, stepNo, content
        );
    }

    @Test
    @DisplayName("토큰이 있으면 200과 레시피 상세/재료 보유여부/부족재료/좋아요 정보를 반환한다")
    void getRecipeDetail_withToken_returnsFullDetail() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/{recipeId}", recipeId)
                        .header("Authorization", "Bearer " + token))
                // 200 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                // 레시피 상세 정보 (이름, 설명, 카테고리, 조리시간, 난이도)
                .andExpect(jsonPath("$.data.recipeId", is(recipeId.intValue())))
                .andExpect(jsonPath("$.data.recipeName", is("된장찌개")))
                .andExpect(jsonPath("$.data.description", is("집에 있는 재료로 간편하게 끓이는 구수한 된장찌개")))
                .andExpect(jsonPath("$.data.category").exists())
                .andExpect(jsonPath("$.data.cookTime", is(20)))
                .andExpect(jsonPath("$.data.difficulty", is("EASY")))
                // 좋아요 정보 (토큰 있을 때 liked, likeCount 반환)
                .andExpect(jsonPath("$.data.likeCount", is(1)))
                .andExpect(jsonPath("$.data.liked", is(true)))
                // 재료 목록 및 각 재료별 보유 여부(owned)
                .andExpect(jsonPath("$.data.ingredients.length()", is(3)))
                .andExpect(jsonPath("$.data.ingredients[0].ingredientName", is(tofuName)))
                .andExpect(jsonPath("$.data.ingredients[0].owned", is(true)))
                .andExpect(jsonPath("$.data.ingredients[1].ingredientName", is(doenjangName)))
                .andExpect(jsonPath("$.data.ingredients[1].owned", is(false)))
                .andExpect(jsonPath("$.data.ingredients[2].ingredientName", is(greenOnionName)))
                .andExpect(jsonPath("$.data.ingredients[2].owned", is(false)))
                // 부족 재료 목록 (보유하지 않은 재료만)
                .andExpect(jsonPath("$.data.missingIngredients", containsInAnyOrder(doenjangName, greenOnionName)))
                // 조리 과정 순서대로 반환
                .andExpect(jsonPath("$.data.steps.length()", is(3)))
                .andExpect(jsonPath("$.data.steps[*].stepOrder", contains(1, 2, 3)))
                .andExpect(jsonPath("$.data.steps[0].content", is("냄비에 멸치육수 2컵을 끓입니다.")))
                .andExpect(jsonPath("$.data.steps[1].content", is("된장 2큰술을 풀어줍니다.")))
                .andExpect(jsonPath("$.data.steps[2].content", is("두부, 대파를 넣고 끓이면 완성.")));
    }

    @Test
    @DisplayName("토큰이 없어도 200으로 조회되며 liked는 null, 모든 재료가 부족 재료로 반환된다")
    void getRecipeDetail_withoutToken_returnsLikedNull() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/{recipeId}", recipeId))
                // 인증 없이 접근 가능 + 200 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.data.recipeName", is("된장찌개")))
                // likeCount는 항상 반환
                .andExpect(jsonPath("$.data.likeCount", is(1)))
                // 토큰 없으면 liked: null
                .andExpect(jsonPath("$.data.liked", is(nullValue())))
                // 보유 정보 없으므로 모든 재료 owned=false
                .andExpect(jsonPath("$.data.ingredients.length()", is(3)))
                .andExpect(jsonPath("$.data.ingredients[*].owned", contains(false, false, false)))
                .andExpect(jsonPath("$.data.missingIngredients", containsInAnyOrder(tofuName, doenjangName, greenOnionName)));
    }

    @Test
    @DisplayName("존재하지 않는 recipeId를 요청하면 404를 반환한다")
    void getRecipeDetail_notFound_returns404() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/{recipeId}", 999_999_999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("레시피를 찾을 수 없습니다.")));
    }
}
