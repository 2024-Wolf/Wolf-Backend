package com.kdt.wolf.domain.user.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.info.impl.GoogleOAuth2UserInfo;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
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

    @Value("${google.client.id}")
    private String googleClientId;

    private final JwtTokenProvider tokenProvider;

    public TokenResponse googleLogin(String idToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                                            .Builder(new NetHttpTransport(), new GsonFactory())
                                            .setAudience(List.of(googleClientId))
                                            .build();
        try {
            GoogleIdToken googleIdToken = verifier.verify(idToken);

            if (googleIdToken == null) {
                throw new BusinessException(ExceptionCode.TOKEN_VALIDATION_FAILED);
            }
            else {
                GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(googleIdToken.getPayload());
                UserEntity user = userDao.signUpOrSignIn(userInfo);
                return tokenProvider.generateJwtTokenResponse(user);
                // TODO : 분기처리 필요
            }
        } catch (IllegalArgumentException | HttpClientErrorException | GeneralSecurityException | IOException e) {
            throw new BusinessException(ExceptionCode.TOKEN_VALIDATION_FAILED);
        }
    }

}
