package com.kdt.wolf.domain.admin.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.dto.AdminAuthDto.AdminLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthService {
    private final AdminDao adminDao;

    public Long login(AdminLoginRequest request) {
        adminDao.signIn(request.email(), request.password());
        return 1L;
    }

}
