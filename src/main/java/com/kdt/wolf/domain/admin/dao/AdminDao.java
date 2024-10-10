package com.kdt.wolf.domain.admin.dao;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Transactional
public class AdminDao {
    private final AdminRepository adminRepository;

    public AdminEntity findById(Long adminId) {

        return adminRepository.findById(adminId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_ADMIN));
    }

    private AdminEntity findByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_ADMIN));
    }

    public AdminEntity signIn(String email, String password) {
        AdminEntity admin = findByEmail(email);
        if (!admin.getPassword().equals(password)) {
            throw new BusinessException(ExceptionCode.INVALID_PASSWORD);
        }
        return admin;
    }
}
