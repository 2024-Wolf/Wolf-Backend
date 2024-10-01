package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/*")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
}
