package com.kdt.wolf.domain.alert.service;

import com.kdt.wolf.domain.alert.dao.AlertDao;
import com.kdt.wolf.domain.alert.dto.AlertDto.AlertRequest;
import com.kdt.wolf.domain.alert.dto.AlertDto.AlertResponse;
import com.kdt.wolf.domain.alert.entity.AlertEntity;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import com.kdt.wolf.global.fcm.service.FcmService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final FcmService fcmService;
    private final AlertDao alertDao;
    private final UserDao userDao;


    public List<AlertResponse> getAlarms(Long userId) {
        return alertDao.getAlarms(userId);
    }

    public List<AlertResponse> getAlarmsPreview(Long userId) {
        return alertDao.getAlarmsPreview(userId);
    }

    public void createAlert(AlertRequest request) {
        try {
            saveAlert(request);
        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.ALERT_SAVE_FAIL);
        }

        try {
            String response = sendAlert(request);
            if(response == null) {
                throw new BusinessException(ExceptionCode.FCM_SEND_FAIL);
            }
        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.FCM_SEND_FAIL);
        }
    }

    private void saveAlert(AlertRequest request) {
        UserEntity user = userDao.findById(request.targetUserId());
        alertDao.saveAlert(AlertEntity.createOf(user, request));
    }

    public String sendAlert(AlertRequest request) {
        return fcmService.sendNotificationByToken(request.toFCMNotificationRequestDto());
    }

    public Long readAlarm(Long alertId) {
        AlertEntity alert = alertDao.getAlarm(alertId);
        alert.makeRead();
        return alert.getAlertId();
    }
}
