package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.challenge.dto.response.ChallengeListResponse;
import com.kdt.wolf.domain.challenge.service.ChallengeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/*")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges/{groupPostId}")
    public List<ChallengeListResponse> getAllChallenges(@PathVariable Long groupPostId, @AuthenticationPrincipal AuthenticatedUser user) {
        return challengeService.getAllChallenges(groupPostId, user.getUserId());
    }
}
