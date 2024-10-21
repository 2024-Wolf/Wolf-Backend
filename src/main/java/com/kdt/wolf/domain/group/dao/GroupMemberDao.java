package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.EvaluateRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.repository.GroupMemberRepository;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.ActivityMetricsEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.ActivityMetricsRepository;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupMemberDao {
    private final GroupMemberRepository groupMemberRepository;
    private final GroupPostRepository groupPostRepository;
    private final ActivityMetricsRepository activityMetricsRepository;

    public List<GroupMemberEntity> findAllByGroupId(Long groupId) {
        GroupPostEntity post = groupPostRepository.findById(groupId).orElseThrow(NotFoundException::new);
        return groupMemberRepository.findAllByGroupPost(post);
    }

    public GroupMemberEntity findByGroupIdAndMemberId(Long groupId, Long memberId) {
        GroupPostEntity group = groupPostRepository.findById(groupId).orElseThrow(NotFoundException::new);

        return groupMemberRepository.findByGroupPostAndGroupMemberId(group, memberId)
                .orElseThrow(NotFoundException::new);
    }

    public void addEvaluation(GroupMemberEntity member, EvaluateRequest request) {
        UserEntity user = member.getUser();
        ActivityMetricsEntity activityMetrics = activityMetricsRepository.findByUserId(user.getUserId())
                .orElseThrow(NotFoundException::new);
        switch (request.getRate()){
            case "good" -> activityMetrics.addGood();
            case "soso" -> activityMetrics.addSoso();
            case "bad" -> activityMetrics.addBad();
            default -> throw new BusinessException(ExceptionCode.BAD_REQUEST);
        }

    }

    public Page<GroupPostEntity> findOngoingPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        return groupMemberRepository.findOngoingPostsByUserIdAndType(userId, type, pageable);
    }

    public Page<GroupPostEntity> findCompletedPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        return groupMemberRepository.findCompletedPostsByUserIdAndType(userId, type, pageable);
    }

    public Long countByGroupPostId(Long groupPostId) {
        return groupMemberRepository.countByGroupPostId(groupPostId);
    }
}
