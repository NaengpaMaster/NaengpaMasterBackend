package com.naengpa.naengpamasterbackend.global.security;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.entity.MemberStatus;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    void adminInactiveMemberListIncludesDeletedAtMember() throws Exception {
        Member admin = findOrCreateAdmin("security-inactive-list-admin@example.com", "탈퇴목록관리자");
        Member target = findOrCreateUser("security-deleted-at-only@example.com", "탈퇴일시만있는회원");
        jdbcTemplate.update(
                "UPDATE members SET status = ?, deleted_at = CURRENT_TIMESTAMP WHERE member_id = ?",
                MemberStatus.ACTIVE.name(),
                target.getId()
        );
        String accessToken = jwtTokenProvider.createAccessToken(admin.getEmail(), "ADMIN");

        mockMvc.perform(get("/api/v1/admin/members")
                        .param("role", "USER")
                        .param("status", "INACTIVE")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("security-deleted-at-only@example.com")))
                .andExpect(content().string(containsString("\"deletedAt\"")));

        mockMvc.perform(get("/api/v1/admin/members")
                        .param("role", "USER")
                        .param("status", "ACTIVE")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("security-deleted-at-only@example.com"))));
    }

    @Test
    void userCannotUpdateMemberStatus() throws Exception {
        Member user = findOrCreateUser("security-status-user@example.com", "상태변경일반회원");
        Member target = findOrCreateUser("security-status-target@example.com", "상태변경대상");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(patch("/api/v1/admin/members/{memberId}/status", target.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "status": "INACTIVE"
                                }
                                """))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("접근 권한이 없습니다.")));

        Member unchangedMember = memberRepository.findById(target.getId()).orElseThrow();
        assertThat(unchangedMember.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(unchangedMember.getDeletedAt()).isNull();
    }

    @Test
    void adminCanDeactivateMemberWithoutDeletingData() throws Exception {
        Member admin = findOrCreateAdmin("security-status-admin@example.com", "상태변경관리자");
        Member target = findOrCreateUser("security-status-inactive@example.com", "탈퇴처리대상");
        scoreRepository.save(Score.createInitial(target.getId()));
        String refreshToken = jwtTokenProvider.createRefreshToken(target.getEmail(), "USER");
        refreshTokenRepository.save(RefreshToken.builder()
                .member(target)
                .refreshToken(refreshToken)
                .expiredAt(LocalDateTime.now().plusDays(1))
                .build());
        String accessToken = jwtTokenProvider.createAccessToken(admin.getEmail(), "ADMIN");

        mockMvc.perform(patch("/api/v1/admin/members/{memberId}/status", target.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "status": "INACTIVE"
                                }
                                """))
                .andExpect(status().isOk());

        Member inactiveMember = memberRepository.findById(target.getId()).orElseThrow();
        assertThat(inactiveMember.getStatus()).isEqualTo(MemberStatus.INACTIVE);
        assertThat(inactiveMember.getDeletedAt()).isNotNull();
        assertThat(scoreRepository.findByMemberId(target.getId())).isPresent();
        assertThat(refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow().isExpired()).isTrue();
    }

    @Test
    void inactiveMemberCannotLoginOrSignupAgain() throws Exception {
        Member inactiveMember = findOrCreateLoginUser("security-withdrawn@example.com", "탈퇴로그인회원", "password!");
        inactiveMember.updateStatus(MemberStatus.INACTIVE);
        memberRepository.saveAndFlush(inactiveMember);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "security-withdrawn@example.com",
                                  "password": "password!"
                                }
                                """))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("탈퇴 처리된 회원입니다.")));

        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "security-withdrawn@example.com",
                                  "password": "password!",
                                  "passwordConfirm": "password!",
                                  "nickname": "탈퇴재가입테스트",
                                  "householdType": "ONE_PERSON"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("탈퇴한 이메일은 재가입할 수 없습니다.")));
    }

    @Test
    void adminCanRestoreInactiveMember() throws Exception {
        Member admin = findOrCreateAdmin("security-restore-admin@example.com", "복구관리자");
        Member target = findOrCreateLoginUser("security-restore-user@example.com", "복구대상", "password!");
        target.updateStatus(MemberStatus.INACTIVE);
        memberRepository.saveAndFlush(target);
        String accessToken = jwtTokenProvider.createAccessToken(admin.getEmail(), "ADMIN");

        mockMvc.perform(patch("/api/v1/admin/members/{memberId}/status", target.getId())
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "status": "ACTIVE"
                                }
                                """))
                .andExpect(status().isOk());

        Member restoredMember = memberRepository.findById(target.getId()).orElseThrow();
        assertThat(restoredMember.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(restoredMember.getDeletedAt()).isNull();

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "security-restore-user@example.com",
                                  "password": "password!"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"accessToken\"")));
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
        String suffix = String.valueOf(System.nanoTime());
        String email = "security-score-signup-" + suffix + "@example.com";

        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "%s",
                                  "password": "password!",
                                  "passwordConfirm": "password!",
                                  "nickname": "초기점수테스트%s",
                                  "householdType": "ONE_PERSON"
                                }
                                """.formatted(email, suffix)))
                .andExpect(status().isCreated());

        Member member = memberRepository.findByEmail(email).orElseThrow();
        Score score = scoreRepository.findByMemberId(member.getId()).orElseThrow();

        assertThat(score.getScore()).isEqualTo(10);
    }

    @Test
    void signupRejectsDuplicateEmail() throws Exception {
        findOrCreateUser("security-duplicate-email@example.com", "중복이메일회원");

        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "security-duplicate-email@example.com",
                                  "password": "password!",
                                  "passwordConfirm": "password!",
                                  "nickname": "중복이메일테스트",
                                  "householdType": "ONE_PERSON"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("이미 사용 중인 이메일입니다.")));
    }

    @Test
    void signupRejectsPasswordMismatch() throws Exception {
        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "security-password-mismatch@example.com",
                                  "password": "password!",
                                  "passwordConfirm": "different!",
                                  "nickname": "비밀번호불일치",
                                  "householdType": "ONE_PERSON"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("비밀번호가 일치하지 않습니다.")));
    }

    @Test
    void signupRejectsMissingRequiredFields() throws Exception {
        mockMvc.perform(post("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "",
                                  "password": "",
                                  "passwordConfirm": "",
                                  "nickname": "필수값누락",
                                  "householdType": "ONE_PERSON"
                }
                """))
        .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("\"success\":false")));
    }

    @Test
    void loginRejectsMissingEmailAndPassword() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "",
                                  "password": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("공백일 수 없습니다.")));
    }

    @Test
    void loginRejectsWrongPassword() throws Exception {
        findOrCreateLoginUser("security-login-fail@example.com", "로그인실패회원", "password!");

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "security-login-fail@example.com",
                                  "password": "wrong!"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("올바르지 않은 이메일 또는 비밀번호입니다.")));
    }

    @Test
    void logoutExpiresRefreshToken() throws Exception {
        Member user = findOrCreateUser("security-logout@example.com", "로그아웃테스트");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail(), "USER");
        refreshTokenRepository.save(RefreshToken.builder()
                .member(user)
                .refreshToken(refreshToken)
                .expiredAt(LocalDateTime.now().plusDays(1))
                .build());

        mockMvc.perform(post("/api/v1/auth/logout")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "refreshToken": "%s"
                                }
                                """.formatted(refreshToken)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("로그아웃되었습니다.")));

        assertThat(refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow().isExpired()).isTrue();
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
    void memberProfileRejectsInvalidHouseholdType() throws Exception {
        Member user = findOrCreateUser("security-profile-invalid-household@example.com", "가구유형예외");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(patch("/api/v1/members/me/profile")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nickname": "가구유형예외수정",
                                  "householdType": "FOUR_PERSON",
                                  "favoriteFoods": [],
                                  "avoidProductIds": []
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void memberProfileRejectsUnknownFavoriteFood() throws Exception {
        Member user = findOrCreateUser("security-profile-food@example.com", "선호음식예외");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(patch("/api/v1/members/me/profile")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nickname": "선호음식예외수정",
                                  "householdType": "ONE_PERSON",
                                  "favoriteFoods": ["없는음식"],
                                  "avoidProductIds": []
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("존재하지 않는 선호 음식이 포함되어 있습니다.")));
    }

    @Test
    void memberProfileRejectsUnknownAvoidProduct() throws Exception {
        Member user = findOrCreateUser("security-profile-product@example.com", "못먹는재료예외");
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), "USER");

        mockMvc.perform(patch("/api/v1/members/me/profile")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nickname": "못먹는재료예외수정",
                                  "householdType": "ONE_PERSON",
                                  "favoriteFoods": [],
                                  "avoidProductIds": [99999999]
                                }
                """))
        .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("존재하지 않는 재료가 포함되어 있습니다.")));
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

    private Member findOrCreateLoginUser(String email, String nickname, String password) {
        return memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.createUser(email, passwordEncoder.encode(password), nickname, null)
                ));
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
