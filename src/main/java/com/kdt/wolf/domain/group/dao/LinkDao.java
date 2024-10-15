package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.LinkRequest;
import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.LinkType;
import com.kdt.wolf.domain.group.repository.ExternalLinksRepository;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LinkDao {
    private final GroupPostRepository groupPostRepository;
    private final ExternalLinksRepository externalLinksRepository;
    private final UserRepository userRepository;

    public List<ExternalLinksEntity> findAllByGroupId(Long groupId) {
        GroupPostEntity group = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);
        return externalLinksRepository.findALLByGroupPost(group);
    }

    public void createLink(Long groupId, LinkRequest request) {
        GroupPostEntity group = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);

        ExternalLinksEntity link = ExternalLinksEntity.builder()
                .groupPost(group)
                .linkType(LinkType.valueOf(request.getLinkType().toUpperCase()))
                .linkUrl(request.getLinkUrl())
                .build();
        externalLinksRepository.save(link);
    }

    public void updateLink(Long linkId, LinkRequest request) {
        ExternalLinksEntity linkEntity = externalLinksRepository.findById(linkId)
                .orElseThrow(NotFoundException::new);

        linkEntity.updateLink(request);
        externalLinksRepository.save(linkEntity);
    }

    public void deleteLink(Long linkId) {
        if(!externalLinksRepository.existsById(linkId)) {
            throw new NotFoundException();
        }
        externalLinksRepository.deleteById(linkId);
    }
}
