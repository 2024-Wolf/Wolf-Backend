package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fcm/*")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService alertService;

}
