package com.naengpa.naengpamasterbackend.global.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider();
        ReflectionTestUtils.setField(
                jwtTokenProvider,
                "jwtSecret",
                "MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDE="
        );
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpiration", 3_600_000L);
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtRefreshExpiration", 86_400_000L);
        jwtTokenProvider.init();
    }

    @Test
    void refreshTokenContainsUniqueJwtId() {
        String firstToken = jwtTokenProvider.createRefreshToken("user@example.com", "USER");
        String secondToken = jwtTokenProvider.createRefreshToken("user@example.com", "USER");

        assertThat(firstToken).isNotEqualTo(secondToken);
    }
}
