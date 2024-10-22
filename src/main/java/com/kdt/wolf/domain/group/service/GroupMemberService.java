package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.EvaluateRequest;
import com.kdt.wolf.domain.group.dto.response.GroupMemberResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.global.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    private final GroupMemberDao groupMemberDao;
    private final RecruitmentsDao recruitmentsDao;

    public List<GroupMemberResponse> getGroupMembers(Long groupId) {
        List<GroupMemberEntity> memberEntities = groupMemberDao.findAllByGroupId(groupId);
        return memberEntities.stream()
                .map(GroupMemberResponse::new)
                .toList();
    }

    public void addEvaluation(Long groupId, List<EvaluateRequest> requests) {
        for (EvaluateRequest request : requests) {
            GroupMemberEntity member = groupMemberDao.findByGroupIdAndMemberId(groupId, request.getMemberId());
            groupMemberDao.addEvaluation(member, request);
        }
    }

    public GroupPostPageResponse getOngoingPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {

        Page<GroupPostEntity> posts = groupMemberDao.findOngoingPostsByUserIdAndType(userId, type, pageable);

        if(posts.isEmpty()) {
            return new GroupPostPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new GroupPostPageResponse(
                posts.getContent().stream().map(
                        post -> new GroupPostResponse(
                                post,
                                recruitmentsDao.findByGroupPost(post).stream()
                                        .map(recruitment -> new Recruitments(
                                                recruitment.getRecruitRole(),
                                                recruitment.getRecruitRoleCnt()
                                        ))
                                        .toList()
                        )
                ).toList(),
                new PageResponse(posts)
        );
    }

    public GroupPostPageResponse getCompletedPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        Page<GroupPostEntity> posts = groupMemberDao.findCompletedPostsByUserIdAndType(userId, type, pageable);

        if(posts.isEmpty()) {
            return new GroupPostPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new GroupPostPageResponse(
                posts.getContent().stream().map(
                        post -> new GroupPostResponse(
                                post,
                                recruitmentsDao.findByGroupPost(post).stream()
                                        .map(recruitment -> new Recruitments(
                                                recruitment.getRecruitRole(),
                                                recruitment.getRecruitRoleCnt()
                                        ))
                                        .toList()
                        )
                ).toList(),
                new PageResponse(posts)
        );
    }
}
