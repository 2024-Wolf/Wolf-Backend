package com.kdt.wolf.domain.link.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.link.dao.LinkDao;
import com.kdt.wolf.domain.link.dto.LinkRequest;
import com.kdt.wolf.domain.link.dto.LinkResponse;
import com.kdt.wolf.domain.link.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkDao linkDao;
    private final GroupPostDao groupPostDao;
    private final UserDao userDao;

    public List<LinkResponse> getLinks(Long groupId) {
        GroupPostEntity group = findGroupById(groupId);
        List<ExternalLinksEntity> links = linkDao.findAll(group);
        return links.stream()
                .map(LinkResponse::new)
                .toList();
    }

    public List<LinkResponse> getLinksByUserId(Long userId) {
        UserEntity user = findUserById(userId);
        List<ExternalLinksEntity> links = linkDao.findAll(user);
        return links.stream()
                .map(LinkResponse::new)
                .toList();
    }

    public void addLink(Long groupId, LinkRequest request) {
        GroupPostEntity group = findGroupById(groupId);
        linkDao.createLink(group, request);
    }

    public Long addLinkByUserId(Long userId, LinkRequest request) {
        UserEntity user = findUserById(userId);
        return linkDao.createLink(user, request);
    }

    public void editLink(Long linkId, LinkRequest request) {
        linkDao.updateLink(linkId, request);
    }

    public void deleteLink(Long linkId) {
        linkDao.deleteLink(linkId);
    }

    private GroupPostEntity findGroupById(Long groupId) {
        return groupPostDao.findById(groupId);
    }

    private UserEntity findUserById(Long userId) {
        return userDao.findById(userId);
    }
}
