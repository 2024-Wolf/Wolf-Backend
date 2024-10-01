package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;

    public void googleLogin(String idToken) {
        // 구글 로그인 로직
    }
}
