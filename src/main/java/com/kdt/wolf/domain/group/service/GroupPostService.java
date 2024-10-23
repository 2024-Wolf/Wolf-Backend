package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewPageResponse;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupPostService {

    private final GroupPostDao groupPostDao;
    private final RecruitmentsDao recruitmentsDao;
    private final UserDao userDao;

    public  GroupPostResponse getGroupPostById(Long groupPostId) {
        GroupPostEntity groupPostEntity = groupPostDao.findById(groupPostId);
        List<RecruitmentsEntity> recruitments = recruitmentsDao.findByGroupPost(groupPostEntity);
        return new GroupPostResponse(
                groupPostEntity,
                recruitments.stream().map(recruitment -> new Recruitments(
                        recruitment.getRecruitRole(),
                        recruitment.getRecruitRoleCnt()
                )).toList()
        );
    }

    public GroupPostPageResponse getPostsByType(String type, Pageable pageable) {
        Page<GroupPostEntity> posts = groupPostDao.findByType(type, pageable);

        if(posts.isEmpty()) {
            return new GroupPostPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new GroupPostPageResponse(
                posts.getContent().stream().map( post -> new GroupPostResponse(
                                post,
                                recruitmentsDao.findByGroupPost(post).stream()
                                        .map(recruitment -> new Recruitments(
                                                recruitment.getRecruitRole(),
                                                recruitment.getRecruitRoleCnt()
                                        )).toList()
                )).toList(),
                new PageResponse(posts)
        );
    }

    public void createPost(GroupPostRequest request, Long userId) {
        UserEntity user = findUserById(userId);

        //예외 처리
        if (request.getType().equals("project") && (request.getRecruitments() == null || request.getRecruitments().isEmpty())) {
            throw new BusinessException(ExceptionCode.BAD_REQUEST);
        }

        GroupPostEntity post = groupPostDao.createPost(request, user);


        //프로젝트면 Recruitments 저장
        if (request.getType().equals("project")) {
            List<Recruitments> recruitmentsList = request.getRecruitments();

            recruitmentsList.forEach(recruitment -> {
                RecruitmentsEntity recruitmentsEntity = RecruitmentsEntity.builder()
                        .groupPost(post)
                        .recruitRole(recruitment.getRecruitRole())
                        .recruitRoleCnt(recruitment.getRecruitRoleCnt())
                        .build();

                recruitmentsDao.save(recruitmentsEntity);
            });
        }
    }

    private UserEntity findUserById(Long userId) {
        return userDao.findById(userId);
    }

    public List<GroupPostResponse> searchPosts(String keyword) {
        List<GroupPostEntity> posts = groupPostDao.findByKeyword(keyword);
        return posts.stream()
                .map(post -> new GroupPostResponse(
                        post,
                        recruitmentsDao.findByGroupPost(post).stream()
                                .map(recruitment -> new Recruitments(
                                        recruitment.getRecruitRole(),
                                        recruitment.getRecruitRoleCnt()
                                )).toList()
                )).toList();
    }
    @Transactional
    public void editGroupPost(Long postId, GroupPostRequest request, Long userId) {
        GroupPostEntity groupPost = groupPostDao.findById(postId);
        if(!groupPost.getLeaderUser().getUserId().equals(userId)) {
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
        groupPost.updateGroupPost(request);
    }

    public void deleteGroupPost(Long postId) {
        groupPostDao.deleteById(postId);
    }
}
