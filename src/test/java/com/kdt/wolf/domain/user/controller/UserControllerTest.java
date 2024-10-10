package com.kdt.wolf.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.domain.user.service.UserService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.auth.dto.UserRoleType;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.base.ApiResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    UserEntity userEntity;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    String accessToken;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .email("testEmail")
                .nickname("nickname")
                .build();

        userEntity = userRepository.save(userEntity);
        accessToken = jwtTokenProvider.generateAccessToken(userEntity, System.currentTimeMillis());
    }

    @Test
    @WithMockUser // 인증된 유저로 동작
    void testCompleteSignUpProcess() throws Exception {
        SignUpRequest request = new SignUpRequest("nickname", "jobTitle", 5, "organization", "interests");
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(userEntity, UserRoleType.USER);

        Mockito.doNothing().when(userService).completeSignUpProcess(eq(authenticatedUser.getUserId()), any(SignUpRequest.class));

        mockMvc.perform(post("/api/v1/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(ApiResult.ok())));
    }
}