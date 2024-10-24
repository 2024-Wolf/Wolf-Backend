package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.EvaluateRequest;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import com.kdt.wolf.domain.group.repository.GroupMemberRepository;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.ActivityMetricsEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.ActivityMetricsRepository;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
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
    private final UserRepository userRepository;

    public List<GroupMemberEntity> findAllByGroupId(Long groupId) {
        return groupMemberRepository.findAllByGroupId(groupId);
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

    public Page<GroupMemberEntity> findOngoingPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        return groupMemberRepository.findOngoingPostsByUserIdAndType(userId, type, pageable);
    }

    public Page<GroupMemberEntity> findCompletedPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        return groupMemberRepository.findCompletedPostsByUserIdAndType(userId, type, pageable);
    }

    public Long countByGroupPostId(Long groupPostId) {
        return groupMemberRepository.countByGroupPostId(groupPostId);
    }

    public String findGroupMembersNickName(GroupPostEntity group) {
        List<GroupMemberEntity> members = groupMemberRepository.findAllByGroupPost(group);
        return members.stream()
                .filter(member -> member.getRole() == MemberRole.MEMBER)
                .map(member -> member.getUser().getName())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    public List<GroupMemberEntity> findGroupMembers(GroupPostEntity groupPost) {
        return groupMemberRepository.findGroupMembers(groupPost);
    }

    public Long addGroupMember(GroupPostEntity groupPost, UserEntity user, RecruitRole position, MemberRole memberRole) {
        GroupMemberEntity member = GroupMemberEntity.builder()
                .groupPost(groupPost)
                .user(user)
                .role(memberRole)
                .position(position.name())
                .build();
        return groupMemberRepository.save(member).getGroupMemberId();
    }

    public void deleteByGroupPostAndUserId(Long groupId, Long userId) {
        GroupPostEntity groupPost = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        GroupMemberEntity groupMember = groupMemberRepository.findByGroupPostAndUser(groupPost, user)
                .orElseThrow(NotFoundException::new);

        if (groupMember.getRole() == MemberRole.LEADER) {
            if (groupMemberRepository.countByGroupPostId(groupId) == 1) {
                groupPostRepository.delete(groupPost);
            }
            else{
                throw new BusinessException(ExceptionCode.LEADER_CAN_NOT_DELETE);
            }
        }
        else{
            groupMemberRepository.delete(groupMember);
        }
    }

    public void updateRole(Long groupId, Long memberId) {
        GroupPostEntity groupPost = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);
        GroupMemberEntity newLeader = groupMemberRepository.findById(memberId)
                .orElseThrow(NotFoundException::new);
        GroupMemberEntity oldLeader = groupMemberRepository.findByGroupPostAndUser(groupPost, groupPost.getLeaderUser())
                .orElseThrow(NotFoundException::new);

        if (oldLeader == newLeader) {
            return;
        }
        UserEntity user = newLeader.getUser();
        groupPost.updateLeader(user);

        oldLeader.updateRole(MemberRole.MEMBER, newLeader.getPosition());
        newLeader.updateRole(MemberRole.LEADER, null);

        groupPostRepository.save(groupPost);
        groupMemberRepository.save(newLeader);
        groupMemberRepository.save(oldLeader);
    }
}
