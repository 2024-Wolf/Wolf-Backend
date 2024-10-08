package com.kdt.wolf.domain.user.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.wolf.global.auth.dto.LoginDto.GoogleLoginResponse;
import com.kdt.wolf.global.auth.dto.LoginDto.LogoutRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.ReissueAccessTokenRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.domain.user.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    private String fcmtoken = "fcmToken";

    @BeforeEach
    void setUp() {
        GoogleLoginResponse response = authService.loginForTest(fcmtoken);
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

    @Test
    @WithMockUser
    void logout() throws Exception {
        LogoutRequest logoutRequest = new LogoutRequest(tokenResponse.refreshToken(), fcmtoken);

        mockMvc.perform(post("/api/v1/auth/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(logoutRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}