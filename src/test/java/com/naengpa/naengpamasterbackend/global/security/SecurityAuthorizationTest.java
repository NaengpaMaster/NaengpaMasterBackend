package com.naengpa.naengpamasterbackend.global.security;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.global.auth.entity.RefreshToken;
import com.naengpa.naengpamasterbackend.global.auth.repository.RefreshTokenRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.score.entity.Score;
import com.naengpa.naengpamasterbackend.score.repository.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void userCannotAccessAdminApi() throws Exception {
        Member user = findOrCreateUser("security-user@example.com", "권한테스트회원");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(get("/api/v1/admin/members")
                        .param("role", "USER")
                        .param("status", "ACTIVE")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("접근 권한이 없습니다.")));
    }

    @Test
    void adminCanAccessAdminApi() throws Exception {
        Member admin = findOrCreateAdmin("security-admin@example.com", "권한테스트관리자");
        String accessToken = jwtTokenProvider.createAccessToken(admin.getEmail(), "ADMIN");

        mockMvc.perform(get("/api/v1/admin/members")
                        .param("role", "USER")
                        .param("status", "ACTIVE")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"success\":true")));
    }

    @Test
    void memberMeReturnsRole() throws Exception {
        Member user = findOrCreateUser("security-me@example.com", "내정보권한테스트");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(get("/api/v1/members/me")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"role\":\"USER\"")));
    }

    @Test
    void signupCreatesInitialScore() throws Exception {
        String email = "security-score-signup@example.com";

        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "password!",
                                  "passwordConfirm": "password!",
                                  "nickname": "초기점수테스트",
                                  "householdType": "ONE_PERSON"
                                }
                                """.formatted(email)))
                .andExpect(status().isCreated());

        Member member = memberRepository.findByEmail(email).orElseThrow();
        Score score = scoreRepository.findByMemberId(member.getId()).orElseThrow();

        assertThat(score.getScore()).isEqualTo(10);
        assertThat(score.getGradeId()).isEqualTo(1L);
    }

    @Test
    void memberProfileReturnsMyProfileFields() throws Exception {
        Member user = findOrCreateUser("security-profile@example.com", "프로필조회테스트");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(get("/api/v1/members/me/profile")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"email\":\"security-profile@example.com\"")))
                .andExpect(content().string(containsString("\"householdType\"")))
                .andExpect(content().string(containsString("\"favoriteFoods\"")))
                .andExpect(content().string(containsString("\"avoidIngredients\"")));
    }

    @Test
    void memberProfileCanBeUpdated() throws Exception {
        Member user = findOrCreateUser("security-profile-update@example.com", "프로필저장테스트");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");
        createFoodCategory("한식");
        createFoodCategory("분식");
        Long kimchiId = createProduct("김치");
        Long beefId = createProduct("소 채끝살");

        mockMvc.perform(patch("/api/v1/members/me/profile")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nickname": "프로필저장수정",
                                  "householdType": "TWO_PERSON",
                                  "favoriteFoods": ["한식", "분식", "한식"],
                                  "avoidProductIds": [%d, %d]
                                }
                                """.formatted(beefId, kimchiId)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"householdType\":\"TWO_PERSON\"")))
                .andExpect(content().string(containsString("\"nickname\":\"프로필저장수정\"")))
                .andExpect(content().string(containsString("\"favoriteFoods\":[\"한식\",\"분식\"]")))
                .andExpect(content().string(containsString("\"avoidIngredients\":[{\"productId\":" + kimchiId)))
                .andExpect(content().string(containsString("\"productId\":" + beefId)));
    }

    @Test
    void memberProfileRejectsDuplicateNickname() throws Exception {
        Member user = findOrCreateUser("security-profile-nickname@example.com", "프로필닉네임테스트");
        findOrCreateUser("security-profile-other@example.com", "이미있는닉네임");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(patch("/api/v1/members/me/profile")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nickname": "이미있는닉네임",
                                  "householdType": "ONE_PERSON",
                                  "favoriteFoods": [],
                                  "avoidProductIds": []
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("이미 사용 중인 닉네임입니다.")));
    }

    @Test
    void refreshTokenCanBeProvidedInRequestBody() throws Exception {
        Member user = findOrCreateUser("security-refresh-body@example.com", "리프레시본문테스트");
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), "USER");
        refreshTokenRepository.save(RefreshToken.builder()
                .member(user)
                .refreshToken(refreshToken)
                .expiredAt(LocalDateTime.now().plusDays(1))
                .build());

        mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "refreshToken": "%s"
                                }
                                """.formatted(refreshToken)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"accessToken\"")))
                .andExpect(content().string(containsString("\"refreshToken\"")));
    }

    @Test
    void refreshTokenCanBeReusedUntilItExpires() throws Exception {
        Member user = findOrCreateUser("security-refresh-reuse@example.com", "리프레시재사용테스트");
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), "USER");
        refreshTokenRepository.save(RefreshToken.builder()
                .member(user)
                .refreshToken(refreshToken)
                .expiredAt(LocalDateTime.now().plusDays(1))
                .build());

        String content = """
                {
                  "refreshToken": "%s"
                }
                """.formatted(refreshToken);

        mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"refreshToken\":\"" + refreshToken + "\"")));
    }

    private Member findOrCreateUser(String email, String nickname) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(Member.createUser(email, "encoded-password", nickname, null)));
    }

    private Member findOrCreateAdmin(String email, String nickname) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> {
                    Member member = Member.createUser(email, "encoded-password", nickname, null);
                    member.updateRole(MemberRole.ADMIN);
                    return memberRepository.save(member);
                });
    }

    private Long createProduct(String namePrefix) {
        String name = namePrefix + System.nanoTime();
        jdbcTemplate.update(
                "INSERT INTO products (product_category_id, name, is_active, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)",
                1L,
                name,
                true
        );
        return jdbcTemplate.queryForObject("SELECT product_id FROM products WHERE name = ?", Long.class, name);
    }

    private void createFoodCategory(String name) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM food_categories WHERE name = ?",
                Integer.class,
                name
        );
        if (count == null || count == 0) {
            jdbcTemplate.update("INSERT INTO food_categories (name) VALUES (?)", name);
        }
    }
}
