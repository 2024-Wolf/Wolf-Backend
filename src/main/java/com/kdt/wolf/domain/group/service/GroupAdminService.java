package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupDetailResponse;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewPageResponse;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
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

        int ChallengeCount = challengePostDao.countByGroupPostId(groupId).intValue() + 1;


        int memberCount = groupMemberDao.countByGroupPostId(groupId).intValue();
        String groupMembers = groupMemberDao.findGroupMembers(groupPost);
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
                groupPost.getTag()
        );
    }
}
