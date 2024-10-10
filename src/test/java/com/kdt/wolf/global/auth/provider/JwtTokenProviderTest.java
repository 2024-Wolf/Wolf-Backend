package com.kdt.wolf.global.auth.provider;

import static org.junit.jupiter.api.Assertions.*;

import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.UnauthorizedException;
import jakarta.transaction.Transactional;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional  // 테스트 후 데이터 롤백
class JwtTokenProviderTest {
    UserEntity userEntity;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired  // 실제 UserRepository 사용
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .email("testEmail")
                .nickname("testNickname")
                .build();

        userEntity = userRepository.save(userEntity);  // 실제 DB에 저장되면서 ID 생성
    }

    @Test
    void generateJwtTokenResponse() {
        // Given
        long now = (new Date()).getTime() - 5;
        // When
        TokenResponse tokenResponse = jwtTokenProvider.createJwtTokenResponse(userEntity);
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
        long expectedUserId = userEntity.getUserId();
        // When
        String token = jwtTokenProvider.generateAccessToken(userEntity, now);
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
        String expiredToken = jwtTokenProvider.generateAccessToken(userEntity, now);

        assertThrows(UnauthorizedException.class, () -> jwtTokenProvider.validateToken(expiredToken));
    }

    @Test
    @DisplayName("잘못된 토큰 예외 처리")
    void validateToken_InvalidToken_ThrowsUnauthorizedException() {
        String invalidToken = "invalid.token.value";
        assertThrows(UnauthorizedException.class, () -> jwtTokenProvider.validateToken(invalidToken));
    }
}