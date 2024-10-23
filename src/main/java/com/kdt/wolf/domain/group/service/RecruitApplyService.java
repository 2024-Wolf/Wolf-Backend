package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.RecruitApplyDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.GroupPreviewUserDto.GroupPreviewUser;
import com.kdt.wolf.domain.group.dto.GroupPreviewUserDto.GroupPreviewUserPageResponse;
import com.kdt.wolf.domain.group.dto.RecruitApplyDto.ApplicationsMember;
import com.kdt.wolf.domain.group.dto.RecruitApplyDto.RecruitApplyDetail;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.common.ApplyStatus;
import com.kdt.wolf.domain.group.entity.common.GroupNewsActionType;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitApplyService {
    private final RecruitApplyDao recruitApplyDao;
    private final RecruitmentsDao recruitmentsDao;
    private final GroupMemberDao groupMemberDao;
    private final GroupNewsService groupNewsService;

    public RecruitApplyDetail getApplicationsById(Long recruitApplyId) {
        RecruitApplyEntity recruitApply =  recruitApplyDao.getById(recruitApplyId);
        return new RecruitApplyDetail(
                recruitApply.getRecruitApplyId(),
                recruitApply.getUser().getName(),
                recruitApply.getEmail(),
                recruitApply.getPosition().name(),
                recruitApply.getApplicationReason(),
                recruitApply.getIntroduction(),
                recruitApply.getTechStack(),
                recruitApply.getPortfolioLink(),
                recruitApply.getAvailableDays(),
                recruitApply.getAdditionalNotes()
        );
    }



    public void recruitApply(Long postId, Long userId, RecruitApplyRequest request){
        recruitApplyDao.applyToGroup(postId, userId, request);
    }

    public GroupPreviewUserPageResponse getAppliedGroupsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        Page<RecruitApplyEntity> posts = recruitApplyDao.findGroupPostByUserIdAndType(userId, pageable, type);

        if(posts.isEmpty()) {
            return new GroupPreviewUserPageResponse(List.of(), new PageResponse(Page.empty()));
        }


        // 지원 날짜
        return new GroupPreviewUserPageResponse(
                posts.getContent().stream().map(
                        apply -> new GroupPreviewUser(
                                apply.getGroupPost().getGroupPostId(),
                                apply.getGroupPost().getThumbnail(),
                                apply.getGroupPost().getName(),
                                apply.getGroupPost().getTag(),
                                apply.getGroupPost().getType().name(),
                                apply.getGroupPost().getEndDate().toString(),
                                apply.getGroupPost().getChallengeStatus(),
                                apply.getCreatedTime().toLocalDate().toString()
                        )
                ).toList(),
                new PageResponse(posts)
        );
        // 합류 날짜
    }

    public List<ApplicationsMember> getPendingApplicationsByGroupId(Long groupId) {
        return recruitApplyDao.getPendingApplicationsByGroupId(groupId).stream().map(
                        recruitApply -> new ApplicationsMember(
                                recruitApply.getRecruitApplyId(),
                                recruitApply.getUser().getProfilePicture(),
                                recruitApply.getUser().getName(),
                                recruitApply.getPosition().name(),
                                recruitApply.getCreatedTime().toLocalDate().toString()
                        )
        ).toList();
    }

    @Transactional
    public void changeApplicationStatus(Long recruitApplyId, ApplyStatus status) {
        RecruitApplyEntity recruitApply = recruitApplyDao.getById(recruitApplyId);
        if(status.equals(ApplyStatus.ACCEPTED)) {
            //참가지 테이블에 넣기
            Long groupMemberId = groupMemberDao.addGroupMember(recruitApply.getGroupPost(), recruitApply.getUser(), recruitApply.getPosition(), MemberRole.MEMBER);
            if(groupMemberId == null) {
                throw new BusinessException(ExceptionCode.ALREADY_APPLIED);
            }
            groupNewsService.createGroupNews(
                    recruitApply.getGroupPost(),
                    groupNewsService.generateMessage(recruitApply.getUser().getNickname(), GroupNewsActionType.JOIN_GROUP)
            );
        }
        recruitApply.changeStatus(status);
    }
}
