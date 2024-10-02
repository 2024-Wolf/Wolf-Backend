package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupPostService {

    private final GroupPostDao groupPostDao;

    public List<GroupPostResponse> getPostsByOption(String option) {
        List<GroupPostEntity> posts = groupPostDao.findByType(option);
        return posts.stream()
                .map(GroupPostResponse::new)
                .collect(Collectors.toList());
    }

    // 모집 글 생성 메서드
    public void createPost(GroupPostRequest request) {
        groupPostDao.createPost(request);
    }

}
