package com.kdt.wolf.domain.admin.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminDao adminDao;


}
