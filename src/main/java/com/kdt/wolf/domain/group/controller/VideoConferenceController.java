package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.VideoConferenceDto;
import com.kdt.wolf.domain.group.service.VideoConferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/{postId}/video-conference")
public class VideoConferenceController {

    private final VideoConferenceService videoConferenceService;

    public VideoConferenceController(VideoConferenceService videoConferenceService) {
        this.videoConferenceService = videoConferenceService;
    }

    @GetMapping
    public ResponseEntity<VideoConferenceDto> getVideoConference(@PathVariable Long postId) {
        VideoConferenceDto videoConference = videoConferenceService.getVideoConference(postId);
        return ResponseEntity.ok(videoConference);
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinConference(@PathVariable Long postId, @RequestParam String userName) {
        videoConferenceService.joinConference(postId, userName);
        return ResponseEntity.ok(userName + "님이 회의에 성공적으로 참여했습니다.");
    }

    @DeleteMapping("/leave")
    public ResponseEntity<String> leaveConference(@PathVariable Long postId, @RequestParam String userName) {
        videoConferenceService.leaveConference(postId, userName);
        return ResponseEntity.ok(userName + "님이 회의에서 성공적으로 나갔습니다.");
    }
}



