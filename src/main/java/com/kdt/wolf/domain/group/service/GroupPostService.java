package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupPostService {

    private final GroupPostDao groupPostDao;

    public  GroupPostResponse getGroupPostById(Long groupPostId) {
        GroupPostEntity groupPostEntity = groupPostDao.findById(groupPostId);
        return new GroupPostResponse(groupPostEntity);
    }

    public List<GroupPostResponse> getPostsByOption(String option) {
        List<GroupPostEntity> posts = groupPostDao.findByType(option);
        return posts.stream()
                .map(GroupPostResponse::new)
                .toList();
    }

    public void createPost(GroupPostRequest request) {
        groupPostDao.createPost(request);
    }

    public List<GroupPostResponse> searchPosts(String keyword) {
        List<GroupPostEntity> posts = groupPostDao.findByKeyword(keyword);
        return posts.stream()
                .map(GroupPostResponse::new)
                .toList();
    }

    public void editGroupPost(Long postId, GroupPostRequest request) {
        groupPostDao.updateGroupPost(postId, request);
    }

    public void deleteGroupPost(Long postId) {
        groupPostDao.deleteById(postId);
    }
}
