package com.kdt.wolf.domain.link.dao;

import com.kdt.wolf.domain.link.dto.LinkRequest;
import com.kdt.wolf.domain.link.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.LinkType;
import com.kdt.wolf.domain.link.repository.ExternalLinksRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class LinkDao {
    private final ExternalLinksRepository externalLinksRepository;

    public List<ExternalLinksEntity> findAll(GroupPostEntity group) {
        return externalLinksRepository.findALLByGroupPost(group);
    }

    public List<ExternalLinksEntity> findAll(UserEntity user) {
        return externalLinksRepository.findALLByUser(user);
    }

    public void createLink(GroupPostEntity group, LinkRequest request) {
        if(externalLinksRepository.existsByGroupPostAndLinkType(group, LinkType.valueOf(request.getLinkType().toUpperCase()))) {
            throw new BusinessException(ExceptionCode.BAD_REQUEST);
        }

        ExternalLinksEntity link = ExternalLinksEntity.groupBuilder()
                .groupPost(group)
                .linkType(LinkType.valueOf(request.getLinkType().toUpperCase()))
                .linkUrl(request.getLinkUrl())
                .build();
        externalLinksRepository.save(link);
    }

    public Long createLink(UserEntity user, LinkRequest request) {
        ExternalLinksEntity link = ExternalLinksEntity.userBuilder()
                .user(user)
                .linkType(LinkType.valueOf(request.getLinkType().toUpperCase()))
                .linkUrl(request.getLinkUrl())
                .build();
        externalLinksRepository.save(link);
        return link.getLinkId();
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

    public void createLink(ExternalLinksEntity linkEnt) {
        externalLinksRepository.save(linkEnt);
    }

    public void saveAll(List<ExternalLinksEntity> newLinks) {
        externalLinksRepository.saveAll(newLinks);
    }
}
