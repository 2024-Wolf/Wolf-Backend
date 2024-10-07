package com.kdt.wolf.domain.alert.service;

import com.kdt.wolf.domain.alert.dao.AlertDao;
import com.kdt.wolf.domain.alert.dto.AlertDto.AlertResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final AlertDao alertDao;


    public List<AlertResponse> getAlarms(Long userId) {
        return alertDao.getAlarms(userId);
    }

    public List<AlertResponse> getAlarmsPreview(Long userId) {
        return alertDao.getAlarmsPreview(userId);
    }
}
