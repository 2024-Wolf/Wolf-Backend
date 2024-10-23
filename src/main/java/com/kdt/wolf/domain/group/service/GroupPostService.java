package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupNewsDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.GroupNewsDto.GroupNews;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.group.entity.common.GroupNewsActionType;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import com.kdt.wolf.global.service.S3FileService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value.Str;

@Service
@RequiredArgsConstructor
public class GroupPostService {
    private final GroupNewsService groupNewsService;
    private final GroupPostDao groupPostDao;
    private final RecruitmentsDao recruitmentsDao;
    private final UserDao userDao;
    private final GroupNewsDao groupNewsDao;
    private final S3FileService s3FileService;

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

    public Long createPost(GroupPostRequest request, Long userId) {
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
        return post.getGroupPostId();
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

        groupNewsService.createGroupNews(groupPost, GroupNewsActionType.UPDATE_GROUP_INFO.getMessage());
    }

    public void deleteGroupPost(Long postId) {
        groupPostDao.deleteById(postId);
    }

    public List<GroupNews> getGroupNews(Long groupId) {
        return groupNewsDao.getGroupNews(groupId).stream().map(
                groupNewsEntity -> new GroupNews(
                        groupNewsEntity.getGroupNewsId(),
                        groupNewsEntity.getNewsContent(),
                        groupNewsEntity.getCreatedTime().toLocalDate().toString()
                )
        ).toList(
        );
    }

    @Transactional
    public String uploadThumbnail(Long postId, MultipartFile thumbnailImage) {
        GroupPostEntity groupPost = groupPostDao.findById(postId);
        String responseUrl = uploadProfileImage(postId, thumbnailImage);
        groupPost.updateThumbnail(responseUrl);

        String deleteImageUrl = groupPost.getThumbnail();
        if(deleteImageUrl != null && deleteImageUrl.contains("s3.amazonaws.com")) {
            s3FileService.delete(deleteImageUrl);
        }

        return responseUrl;
    }

    private String uploadProfileImage(Long groupId, MultipartFile profileImg) {
        String responseUrl;
        try {
            String path = "group"  + "/" + groupId;
            responseUrl = s3FileService.upload(profileImg, path);
        } catch (IOException e) {
            throw new BusinessException(ExceptionCode.PROFILE_IMAGE_UPLOAD_FAIL);
        }
        return responseUrl;
    }
}
