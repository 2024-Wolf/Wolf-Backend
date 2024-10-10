package com.kdt.wolf.domain.user.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dao.UserLoginResult;
import com.kdt.wolf.global.auth.dto.LoginDto.GoogleLoginResponse;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.info.OAuth2UserInfo;
import com.kdt.wolf.domain.user.info.impl.GoogleOAuth2UserInfo;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.auth.service.RefreshTokenService;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import com.kdt.wolf.global.fcm.service.FcmService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;
    private final RefreshTokenService refreshTokenService;
    private final FcmService fcmService;

    @Value("${google.client.id}")
    private String googleClientId;

    private final JwtTokenProvider tokenProvider;

    public GoogleLoginResponse googleLogin(String idToken, String fcmToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                                            .Builder(new NetHttpTransport(), new GsonFactory())
                                            .setAudience(List.of(googleClientId))
                                            .build();
        try {
            GoogleIdToken googleIdToken = verifier.verify(idToken);

            if (googleIdToken == null) {
                throw new BusinessException(ExceptionCode.ID_TOKEN_VALIDATION_FAILED);
            }
            else {
                OAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(googleIdToken.getPayload());
                UserLoginResult userLoginResult = userDao.signUpOrSignIn(userInfo);
                TokenResponse response = generateJwtTokenResponse(userLoginResult.user());
                saveFcmToken(userLoginResult.user(), fcmToken);
                return new GoogleLoginResponse(response, userLoginResult.flag());
            }
        } catch (IllegalArgumentException | HttpClientErrorException | GeneralSecurityException | IOException e) {
            throw new BusinessException(ExceptionCode.ID_TOKEN_VALIDATION_FAILED);
        }
    }

    private void saveFcmToken(UserEntity user, String fcmToken) {
        if( fcmService.saveFcmToken(user, fcmToken).isEmpty() ) {
            throw new BusinessException(ExceptionCode.FCM_TOKEN_SAVE_FAILED);
        }
    }

    private TokenResponse generateJwtTokenResponse(UserEntity user) {
        refreshTokenService.deleteRefreshTokenByUserId(user.getUserId());

        TokenResponse tokenResponse = tokenProvider.createJwtTokenResponse(user);
        refreshTokenService.saveRefreshToken(user, tokenResponse.refreshToken());
        return tokenResponse;
    }


    public GoogleLoginResponse loginForTest(String fcmToken) {
        OAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(
                "testId",
                "testName",
                "testNickname",
                "testEmail",
                "testImageUrl"
        );
        UserLoginResult userLoginResult = userDao.signUpOrSignIn(userInfo);
        TokenResponse response = generateJwtTokenResponse(userLoginResult.user());
        saveFcmToken(userLoginResult.user(), fcmToken);
        return new GoogleLoginResponse(response, userLoginResult.flag());
    }

    public TokenResponse reissueAccessToken(String accessToken, String refreshToken) {
        // 토큰 검증
        validateRefreshToken(refreshToken);

        // 토큰에서 유저 정보 확인
        long userId = Long.parseLong(tokenProvider.getSubject(accessToken));
        UserEntity user = userDao.findById(userId);

        return generateJwtTokenResponse(user);
    }

    private void validateRefreshToken(String refreshToken) {
        try {
            tokenProvider.validateToken(refreshToken);
        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.REFRESH_TOKEN_VALIDATION_FAILED);
        }
    }

    public void logout(String refreshToken, String fcmToken) {
        validateRefreshToken(refreshToken);
        refreshTokenService.deleteRefreshToken(refreshToken);
        fcmService.deleteFcmToken(fcmToken);
    }

    public Status removeUser(Long userId) {
        // 유저 삭제 우선 safe delete로 구현
        refreshTokenService.deleteRefreshTokenByUserId(userId);
        return userDao.changeUserStatus(userId, Status.WITHDRAWN);
    }
}
