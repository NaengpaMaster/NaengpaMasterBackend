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
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private Member createMember(String label) {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        return memberRepository.save(Member.createUser(
                label + "-" + unique + "@example.com",
                "encoded-password",
                label + unique,
                HouseholdType.ETC
        ));
    }

    private String tokenOf(Member member) {
        return jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
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

    @Test
    @DisplayName("API-308: 로그인 사용자가 댓글을 등록하면 201과 생성된 댓글 ID를 반환하고 DB에 저장된다")
    void createComment_success_returns201() throws Exception {
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/comments", recipeId)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"맛있게 만들어 먹었습니다.\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("댓글이 등록되었습니다.")))
                .andExpect(jsonPath("$.data.commentId", is(notNullValue())));

        Long count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM recipe_comments WHERE recipe_id = ? AND content = ? AND is_deleted = false",
                Long.class, recipeId, "맛있게 만들어 먹었습니다."
        );
        org.junit.jupiter.api.Assertions.assertEquals(1L, count);
    }

    @Test
    @DisplayName("API-308: 댓글 내용이 비어 있으면 400을 반환한다")
    void createComment_blankContent_returns400() throws Exception {
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/comments", recipeId)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"  \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("댓글 내용을 입력해주세요.")));
    }

    @Test
    @DisplayName("API-308: 댓글이 300자를 초과하면 400을 반환한다")
    void createComment_tooLong_returns400() throws Exception {
        String tooLong = "가".repeat(301);

        mockMvc.perform(post("/api/v1/recipes/{recipeId}/comments", recipeId)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"" + tooLong + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("댓글은 300자 이하로 입력해주세요.")));
    }

    @Test
    @DisplayName("API-308: 인증 없이 댓글 등록을 시도하면 401을 반환한다")
    void createComment_withoutAuth_returns401() throws Exception {
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/comments", recipeId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"인증 없이 등록\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("API-308: 존재하지 않는 레시피에 댓글 등록 시 404를 반환한다")
    void createComment_recipeNotFound_returns404() throws Exception {
        mockMvc.perform(post("/api/v1/recipes/{recipeId}/comments", 999_999_999L)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"존재하지 않는 레시피 댓글\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("레시피를 찾을 수 없습니다.")));
    }

    @Test
    @DisplayName("API-309: 작성자가 본인 댓글을 수정하면 200을 반환하고 내용/수정여부가 갱신된다")
    void updateComment_byWriter_returns200() throws Exception {
        Long commentId = insertComment(recipeId, writer.getId(), "수정 전 내용", false);

        mockMvc.perform(patch("/api/v1/comments/{commentId}", commentId)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"수정된 댓글 내용입니다.\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("댓글이 수정되었습니다.")));

        // 목록 조회로 수정된 내용과 수정 여부(modified=true)를 검증
        mockMvc.perform(get("/api/v1/recipes/{recipeId}/comments", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.comments[0].content", is("수정된 댓글 내용입니다.")))
                .andExpect(jsonPath("$.data.comments[0].modified", is(true)));
    }

    @Test
    @DisplayName("API-309: 댓글 내용이 비어 있으면 400을 반환한다")
    void updateComment_blankContent_returns400() throws Exception {
        Long commentId = insertComment(recipeId, writer.getId(), "수정 전 내용", false);

        mockMvc.perform(patch("/api/v1/comments/{commentId}", commentId)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("댓글 내용을 입력해주세요.")));
    }

    @Test
    @DisplayName("API-309: 댓글이 300자를 초과하면 400을 반환한다")
    void updateComment_tooLong_returns400() throws Exception {
        Long commentId = insertComment(recipeId, writer.getId(), "수정 전 내용", false);
        String tooLong = "나".repeat(301);

        mockMvc.perform(patch("/api/v1/comments/{commentId}", commentId)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"" + tooLong + "\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("댓글은 300자 이하로 입력해주세요.")));
    }

    @Test
    @DisplayName("API-309: 작성자가 아닌 사용자가 수정하면 403을 반환한다")
    void updateComment_notWriter_returns403() throws Exception {
        Long commentId = insertComment(recipeId, writer.getId(), "수정 전 내용", false);
        Member other = createMember("타인");

        mockMvc.perform(patch("/api/v1/comments/{commentId}", commentId)
                        .header("Authorization", "Bearer " + tokenOf(other))
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"남의 댓글 수정 시도\"}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("댓글 수정 권한이 없습니다.")));
    }

    @Test
    @DisplayName("API-309: 인증 없이 댓글 수정을 시도하면 401을 반환한다")
    void updateComment_withoutAuth_returns401() throws Exception {
        Long commentId = insertComment(recipeId, writer.getId(), "수정 전 내용", false);

        mockMvc.perform(patch("/api/v1/comments/{commentId}", commentId)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"인증 없이 수정\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("API-309: 존재하지 않는 댓글 수정 시 404를 반환한다")
    void updateComment_notFound_returns404() throws Exception {
        mockMvc.perform(patch("/api/v1/comments/{commentId}", 999_999_999L)
                        .header("Authorization", "Bearer " + writerToken)
                        .contentType(APPLICATION_JSON)
                        .content("{\"content\": \"존재하지 않는 댓글 수정\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.message", is("댓글을 찾을 수 없습니다.")));
    }
}
