package com.kdt.wolf.domain.user.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.wolf.domain.user.dto.LoginDto.GoogleLoginResponse;
import com.kdt.wolf.domain.user.dto.LoginDto.ReissueAccessTokenRequest;
import com.kdt.wolf.domain.user.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.domain.user.service.AuthService;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.base.ApiResult;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    TokenResponse tokenResponse;

    @BeforeEach
    void setUp() {
        GoogleLoginResponse response = authService.loginForTest();
        tokenResponse = new TokenResponse(response.tokenResponse().grantType(),
                                            response.tokenResponse().accessToken(),
                                            response.tokenResponse().refreshToken(),
                                            response.tokenResponse().accessTokenExpiresIn()
        );
    }

    @Test
    @WithMockUser
    void reissueAccessToken() throws Exception {

        ReissueAccessTokenRequest request = new ReissueAccessTokenRequest(tokenResponse.accessToken(), tokenResponse.refreshToken());

        MvcResult result = mockMvc.perform(post("/api/v1/auth/reissue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("accessToken"));
    }
}