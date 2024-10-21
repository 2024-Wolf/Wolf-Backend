package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.ChallengeParticipantMember;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.ChallengePreviewByGroup;
import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupDetailResponse;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewPageResponse;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewResponse;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupAdminService {

    private final GroupPostDao groupPostDao;
    private final GroupMemberDao groupMemberDao;
    private final ChallengePostDao challengePostDao;
    public GroupPreviewPageResponse getPosts(Pageable pageable) {

        Page<GroupPostEntity> posts = groupPostDao.findAll(pageable);

        if(posts.isEmpty()) {
            return new GroupPreviewPageResponse(List.of(), 0, 0, 0);
        }

        return new GroupPreviewPageResponse(
                posts.stream()
                        .map(post -> new GroupPreviewResponse(
                                post.getGroupPostId(),
                                post.getName(),
                                post.getType().name(),
                                post.getStartDate().toString(),
                                post.getEndDate().toString(),
                                groupMemberDao.countByGroupPostId(post.getGroupPostId()).intValue(),
                                post.getChallengeStatus()
                        )).toList(),
                posts.getNumber(),
                posts.getTotalPages(),
                posts.getNumber()
        );
    }

    public GroupDetailResponse getGroupDetail(Long groupId) {
        GroupPostEntity groupPost = groupPostDao.findById(groupId);
        String status = getStatus(groupPost);

        int ChallengeCount = challengePostDao.countByGroupPostId(groupId).intValue() + 1;

        int memberCount = groupMemberDao.countByGroupPostId(groupId).intValue();
        String groupMembers = groupMemberDao.findGroupMembersNickName(groupPost);
        return new GroupDetailResponse(
                groupPost.getGroupPostId(),
                status,
                groupPost.getStartDate().toString(),
                groupPost.getEndDate().toString(),
                groupPost.getType().name(),
                ChallengeCount,
                groupPost.getName(),
                groupPost.getLeaderUser().getName(),
                memberCount,
                groupMembers,
                groupPost.getTag(),
                getChallengePreviewByGroup(groupPost)
        );
    }

    private List<ChallengePreviewByGroup> getChallengePreviewByGroup(GroupPostEntity groupPost) {
        // 그룹의 챌린지 목록을 가져옴
        List<ChallengePostEntity> challenges = challengePostDao.findByGroupPost(groupPost);

        return challenges.stream()
                .map(challenge -> new ChallengePreviewByGroup(
                        challenge.getChallengePostId(),
                        challenge.getTitle(),
                        getChallengeParticipantMembers(groupPost, challenge)
                ))
                .toList();
    }

    // 그룹에 속한 멤버들이 챌린지에 참가하고 있는지 확인
    private List<ChallengeParticipantMember> getChallengeParticipantMembers(
            GroupPostEntity groupPost, ChallengePostEntity challenge) {

        // 그룹에 속한 멤버들 조회
        List<GroupMemberEntity> groupMembers = groupMemberDao.findGroupMembers(groupPost);

        return groupMembers.stream()
                .map(groupMemberEntity -> mapToChallengeParticipantMember(groupMemberEntity, challenge, groupPost))
                .toList();
    }

    // 각 그룹 멤버가 챌린지에 참가 중인지 확인하여 ChallengeParticipantMember 객체 생성
    private ChallengeParticipantMember mapToChallengeParticipantMember(
            GroupMemberEntity groupMemberEntity, ChallengePostEntity challenge, GroupPostEntity groupPost) {

        // 해당 그룹 멤버가 현재 챌린지에 참가 중인지 확인
        return challengePostDao.findParticipants(groupMemberEntity, challenge, groupPost).stream()
                .filter(participantEntity -> groupMemberEntity.getUser().getUserId()
                        .equals(participantEntity.getUser().getUserId()))
                .findFirst()
                .map(participantEntity -> new ChallengeParticipantMember(
                        participantEntity.getUser().getUserId(),
                        participantEntity.getUser().getName(),
                        participantEntity.getParticipationStatus(),
                        participantEntity.getPaymentStatus()
                ))
                .orElseGet(() -> new ChallengeParticipantMember(
                        groupMemberEntity.getUser().getUserId(),
                        groupMemberEntity.getUser().getName(),
                        'N',
                        'N'
                ));
    }

    private String getStatus(GroupPostEntity groupPost) {
        String status = "모집중";
        if(groupPost.getRecruitDeadlineDate().isAfter(LocalDate.now())) {
            if(groupPost.getStartDate().isBefore(LocalDate.now())) {
                status = "모집완료";
            }else if(groupPost.getStartDate().isAfter(LocalDate.now())) {
                status = "진행중";
            }
        } else if (groupPost.getEndDate().isAfter(LocalDate.now())) {
            status = "완료";
        }
        return status;
    }
}
