package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupNewsDao;
import com.kdt.wolf.domain.group.entity.GroupNewsEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupNewsActionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupNewsService {
    private final GroupNewsDao groupNewsDao;

    public void createGroupNews(GroupPostEntity group, String message) {
        groupNewsDao.save(new GroupNewsEntity(message, group));
    }

    // 메시지를 동적으로 생성하는 메서드
    public String generateMessage(String username, GroupNewsActionType action) {
        return String.format("%s님이 %s", username, action.getMessage());
    }

}
