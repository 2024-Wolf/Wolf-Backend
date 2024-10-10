package com.kdt.wolf.global.auth.provider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import com.kdt.wolf.domain.user.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.auth.dto.UserRoleType;
import com.kdt.wolf.global.exception.UnauthorizedException;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtTokenProviderTest {
    UserEntity userEntity;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @MockBean
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);

        userEntity = UserEntity.builder()
                        .email("testEmail")
                        .nickname("testNickname")
                        .build();

        userRepository.save(userEntity);
    }

    @Test
    void generateJwtTokenResponse() {
        // Given
        long now = (new Date()).getTime()-5;
        // When
        TokenResponse tokenResponse = jwtTokenProvider.generateJwtTokenResponse(userEntity, UserRoleType.USER);
        // Then
        assertNotNull(tokenResponse);
        assertNotNull(tokenResponse.accessToken());
        assertNotNull(tokenResponse.refreshToken());
        assertTrue(tokenResponse.accessTokenExpiresIn() > now);
    }

    @Test
    void generateAccessTokenValue() {
        long now = (new Date()).getTime();
        // Given
        long expectedUserId = 1L;
        // When
        String token = jwtTokenProvider.generateAccessTokenValue(userEntity, now, UserRoleType.USER);
        // Then
        assertNotNull(token);
        assertTrue(token.contains(Long.toString(expectedUserId)));
    }

    @Test
    void createRefreshToken() {
        // Given
        long now = (new Date()).getTime();
        // When
        String refreshToken = jwtTokenProvider.createRefreshToken(now);
        // Then
        assertNotNull(refreshToken);
    }

    @Test
    @DisplayName("만료된 토큰 예외 처리")
    void validateToken() {
        long now = (new Date()).getTime() - 10000 * 60 * 60;
        String expiredToken = jwtTokenProvider.generateAccessTokenValue(userEntity, now, UserRoleType.USER);
        // When & Then: 만료된 토큰을 전달했을 때 예외가 발생하는지 확인
        assertThrows(UnauthorizedException.class, () -> jwtTokenProvider.validateToken(expiredToken));
    }

    @Test
    @DisplayName("잘못된 토큰 예외 처리")
    void validateToken_InvalidToken_ThrowsUnauthorizedException() {
        // Given: 잘못된 JWT 토큰
        String invalidToken = "invalid.token.value";
        // When & Then: 잘못된 토큰을 전달했을 때 예외가 발생하는지 확인
        assertThrows(UnauthorizedException.class, () -> jwtTokenProvider.validateToken(invalidToken));
    }
}