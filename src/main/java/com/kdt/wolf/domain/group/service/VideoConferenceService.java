package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dto.VideoConferenceDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoConferenceService {

    private Map<Long, List<String>> conferenceParticipants = new HashMap<>();

    public VideoConferenceDto getVideoConference(Long postId) {
        String conferenceName = "Group Meeting for Post " + postId;
        return new VideoConferenceDto(postId, conferenceName + " 정보입니다.");
    }

    public void joinConference(Long postId, String userName) {
// 참가자 목록 생성
        conferenceParticipants.putIfAbsent(postId, new ArrayList<>());
        List<String> participants = conferenceParticipants.get(postId);

        if (!participants.contains(userName)) {
            participants.add(userName);
        }

// WebSocket 또는 WEBRTC 연결 로직 추가 가능
        System.out.println(userName + "님이 회의에 참여했습니다.");
    }


    public void leaveConference(Long postId, String userName) {
        List<String> participants = conferenceParticipants.get(postId);

        if (participants != null && participants.contains(userName)) {
            participants.remove(userName);
            System.out.println(userName + "님이 회의에서 나갔습니다.");
        }
    }
}

