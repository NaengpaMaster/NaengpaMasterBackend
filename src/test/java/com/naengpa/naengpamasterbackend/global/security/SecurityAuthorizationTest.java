package com.naengpa.naengpamasterbackend.global.security;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberRole;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
