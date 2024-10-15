package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
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

    public  GroupPostResponse getGroupPostById(Long groupPostId) {
        GroupPostEntity groupPostEntity = groupPostDao.findById(groupPostId);
        return new GroupPostResponse(groupPostEntity);
    }

    public GroupPostPageResponse getPostsByOption(String option, Pageable pageable) {
        Page<GroupPostEntity> posts = groupPostDao.findByType(option, pageable);

        if(posts.isEmpty()) {
            return new GroupPostPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new GroupPostPageResponse(
                posts.getContent().stream().map(GroupPostResponse::new).toList(),
                new PageResponse(posts)
        );
    }

    public void createPost(GroupPostRequest request) {
        //예외 처리
        if (request.getType().equals("project") && (request.getRecruitments() == null || request.getRecruitments().isEmpty())) {
            throw new BusinessException(ExceptionCode.BAD_REQUEST);
        }

        GroupPostEntity post = groupPostDao.createPost(request);

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
