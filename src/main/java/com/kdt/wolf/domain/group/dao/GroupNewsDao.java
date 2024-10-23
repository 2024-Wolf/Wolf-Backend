package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.GroupNewsDto.GroupNews;
import com.kdt.wolf.domain.group.entity.GroupNewsEntity;
import com.kdt.wolf.domain.group.repository.GroupNewsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupNewsDao {
    private final GroupNewsRepository groupNewsRepository;

    public List<GroupNewsEntity> getGroupNews(Long groupId) {
        return groupNewsRepository.findByGroupId(groupId);
    }

    public void save(GroupNewsEntity groupNewsEntity) {
        groupNewsRepository.save(groupNewsEntity);
    }
}
