package com.kdt.wolf.domain.admin.dao;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminDao {
    private final AdminRepository adminRepository;

    public AdminEntity findById(Long adminId) {

        return adminRepository.findById(adminId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_ADMIN));
    }
}
