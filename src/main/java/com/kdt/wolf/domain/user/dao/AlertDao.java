package com.kdt.wolf.domain.user.dao;

import com.kdt.wolf.domain.user.dto.FcmDto.AlertDto;
import com.kdt.wolf.domain.user.entity.AlertEntity;
import com.kdt.wolf.domain.user.repository.AlertRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertDao {
    private final AlertRepository alertRepository;

    public List<AlertDto> getAlarms(Long userId) {

        List<AlertEntity> alerts = alertRepository.findByUserId(userId);
        return alerts
                .stream()
                .map(alert -> {
                    alert.makeRead();
                    return new AlertDto(
                            alert.getAlertContent(),
                            alert.getAlertLink(),
                            alert.getCreatedTime()
                    );
                })
                .toList();
    }
}
