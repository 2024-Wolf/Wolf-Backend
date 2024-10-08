package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.group.repository.RecruitMentsRepository;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GroupPostService {

    private final GroupPostDao groupPostDao;
    private final RecruitMentsRepository recruitMentsRepository;
    private final RecruitmentsDao recruitmentsDao;
    //직군 DAO 필요

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
        //예외 처리
        if (Objects.equals(request.getType(), "project") && (request.getRecruitments().isEmpty())) {
            throw new BusinessException(ExceptionCode.BAD_REQUEST);
        }

        GroupPostEntity post = groupPostDao.createPost(request);

        //프로젝트면 Recruitments 저장
        if (Objects.equals(request.getType(), "project")) {
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
