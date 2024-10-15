package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.LinkDao;
import com.kdt.wolf.domain.group.dto.request.LinkRequest;
import com.kdt.wolf.domain.group.dto.response.LinkResponse;
import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkDao linkDao;

    public List<LinkResponse> getLinks(Long groupId) {
        List<ExternalLinksEntity> links = linkDao.findAllByGroupId(groupId);
        return links.stream()
                .map(LinkResponse::new)
                .toList();
    }

    public void addLink(Long groupId, LinkRequest request) {
        linkDao.createLink(groupId, request);
    }

    public void editLink(Long linkId, LinkRequest request) {
        linkDao.updateLink(linkId, request);
    }

    public void deleteLink(Long linkId) {
        linkDao.deleteLink(linkId);
    }
}
