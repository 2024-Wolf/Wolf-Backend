package com.kdt.wolf.domain.alert.dao;

import com.kdt.wolf.domain.alert.dto.AlertDto.AlertResponse;
import com.kdt.wolf.domain.alert.entity.AlertEntity;
import com.kdt.wolf.domain.alert.repository.AlertRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertDao {
    private final AlertRepository alertRepository;

    public List<AlertResponse> getAlarms(Long userId) {

        List<AlertEntity> alerts = alertRepository.findByUserId(userId);
        return alerts
                .stream()
                .map(alert -> {
                    alert.makeRead();
                    return new AlertResponse(
                            alert.getAlertId(),
                            alert.getType().getType(),
                            alert.getAlertContent(),
                            alert.getAlertLink(),
                            alert.getCreatedTime()
                    );
                })
                .toList();
    }

    public List<AlertResponse> getAlarmsPreview(Long userId) {
        List<AlertEntity> alerts = alertRepository.findUnReadAlarmsByUserId(userId);
        return alerts
                .stream()
                .map(alert -> new AlertResponse(
                        alert.getAlertId(),
                        alert.getType().getType(),
                        alert.getAlertContent(),
                        alert.getAlertLink(),
                        alert.getCreatedTime()
                ))
                .toList();
    }
    public void saveAlert(AlertEntity alert) {
        alertRepository.save(alert);
    }

    public AlertEntity getAlarm(Long alertId) {
        return alertRepository.findById(alertId)
                .orElseThrow(NotFoundException::new);
    }
}
