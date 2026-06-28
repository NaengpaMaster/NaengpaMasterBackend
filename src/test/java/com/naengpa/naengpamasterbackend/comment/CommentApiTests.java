package com.naengpa.naengpamasterbackend.comment;

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

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CommentApiTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private Long recipeId;
    private Member writer;       // 댓글 작성자
    private String writerToken;
    private String writerNickname;

    @BeforeEach
    void setUp() {
        String unique = UUID.randomUUID().toString().substring(0, 8);

        writerNickname = "댓글작성자" + unique;
        writer = memberRepository.save(Member.createUser(
                "comment-writer-" + unique + "@example.com",
                "encoded-password",
                writerNickname,
                HouseholdType.ETC
        ));
        writerToken = jwtTokenProvider.createAccessToken(writer.getEmail(), writer.getRole().name());

        // 카테고리 + 레시피 생성
        Long categoryId = nextId("recipe_categories", "recipe_category_id");
        jdbcTemplate.update(
                "INSERT INTO recipe_categories (recipe_category_id, name) VALUES (?, ?)",
                categoryId, "댓글카테고리-" + unique
        );

        recipeId = nextId("recipes", "recipe_id");
        jdbcTemplate.update(
                "INSERT INTO recipes (recipe_id, recipe_category_id, name, description, cooking_time, difficulty, is_deleted) "
                        + "VALUES (?, ?, ?, ?, ?, ?, false)",
                recipeId, categoryId, "댓글테스트레시피", "댓글 테스트용 레시피", 15, "EASY"
        );
    }

    private Long nextId(String table, String idColumn) {
        return jdbcTemplate.queryForObject(
                "SELECT COALESCE(MAX(" + idColumn + "), 0) + 1 FROM " + table,
                Long.class
        );
    }

    private Long insertComment(Long recipeId, Long memberId, String content, boolean modified) {
        Long commentId = nextId("recipe_comments", "recipe_comment_id");
        jdbcTemplate.update(
                "INSERT INTO recipe_comments (recipe_comment_id, recipe_id, member_id, content, created_at, updated_at, is_deleted) "
                        + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?, false)",
                commentId, recipeId, memberId, content, modified ? java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()) : null
        );
        return commentId;
    }

    @Test
    @DisplayName("API-307: 댓글 목록 조회 시 200과 작성자 닉네임/내용/수정여부/페이지네이션 정보를 반환한다")
    void getComments_returnsListWithPagination() throws Exception {
        insertComment(recipeId, writer.getId(), "첫 번째 댓글입니다.", false);
        insertComment(recipeId, writer.getId(), "두 번째 댓글입니다.", true);

        mockMvc.perform(get("/api/v1/recipes/{recipeId}/comments", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("댓글 목록 조회 성공")))
                // 댓글 목록 (작성 순서대로)
                .andExpect(jsonPath("$.data.comments.length()", is(2)))
                .andExpect(jsonPath("$.data.comments[0].writerNickname", is(writerNickname)))
                .andExpect(jsonPath("$.data.comments[0].content", is("첫 번째 댓글입니다.")))
                .andExpect(jsonPath("$.data.comments[0].createdAt").exists())
                .andExpect(jsonPath("$.data.comments[*].modified", contains(false, true)))
                // 페이지네이션 정보
                .andExpect(jsonPath("$.data.page", is(0)))
                .andExpect(jsonPath("$.data.size", is(10)))
                .andExpect(jsonPath("$.data.totalElements", is(2)))
                .andExpect(jsonPath("$.data.totalPages", is(1)));
    }

    @Test
    @DisplayName("API-307: 인증 없이도 댓글 목록을 정상 조회할 수 있다")
    void getComments_withoutAuth_returnsOk() throws Exception {
        insertComment(recipeId, writer.getId(), "비회원도 볼 수 있는 댓글", false);

        mockMvc.perform(get("/api/v1/recipes/{recipeId}/comments", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.data.comments.length()", is(1)));
    }

    @Test
    @DisplayName("API-307: 존재하지 않는 레시피의 댓글 목록 조회 시 404를 반환한다")
    void getComments_recipeNotFound_returns404() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/{recipeId}/comments", 999_999_999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("레시피를 찾을 수 없습니다.")));
    }
}
