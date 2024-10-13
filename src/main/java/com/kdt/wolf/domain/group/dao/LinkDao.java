package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.repository.ExternalLinksRepository;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class LinkDao {
    private GroupPostRepository groupPostRepository;
    private ExternalLinksRepository externalLinksRepository;

    public List<ExternalLinksEntity> findAllByGroupId(Long groupId) {
        GroupPostEntity group = groupPostRepository.findById(groupId).orElseThrow(NotFoundException::new);
        return externalLinksRepository.findALLByGroupPost(group);
    }
}
