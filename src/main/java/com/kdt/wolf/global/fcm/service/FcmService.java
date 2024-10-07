package com.kdt.wolf.global.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import com.kdt.wolf.global.fcm.repository.FcmRepository;
import com.kdt.wolf.global.fcm.service.dto.FCMNotificationRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmService {
    private final FirebaseMessaging firebaseMessaging;
    private final FcmRepository fcmRepository;

    public String sendNotificationByToken(FCMNotificationRequestDto requestDto) {
        List<String> tokens = fcmRepository.findTokensByUserId(requestDto.getTargetUserId());
        if (tokens.isEmpty()) {
            throw new BusinessException(ExceptionCode.NOT_FOUND);
        }
        Message message = Message.builder()
                .putData("title", requestDto.getTitle())
                .putData("body", requestDto.getBody())
                .putData("link", requestDto.getLink())
                .setToken(tokens.get(0))
                .build();

        try {
            return firebaseMessaging.send(message);
        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.FCM_SEND_FAIL);
        }
    }
}
