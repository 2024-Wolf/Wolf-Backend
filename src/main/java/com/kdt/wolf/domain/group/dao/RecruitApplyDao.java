package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.RecruitApplyDto.ApplicationsMember;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.group.repository.RecruitApplyRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class RecruitApplyDao {
    private final GroupPostRepository groupPostRepository;
    private final UserRepository userRepository;
    private final RecruitApplyRepository recruitApplyRepository;

    public RecruitApplyEntity getById(Long recruitApplyId) {
        return recruitApplyRepository.findById(recruitApplyId)
                .orElseThrow(NotFoundException::new);
    }

    public void applyToGroup(Long postId, Long userId, RecruitApplyRequest request) {
        GroupPostEntity groupPost = groupPostRepository.findById(postId)
                .orElseThrow(NotFoundException::new);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        RecruitRole position = "frontend".equals(request.getPosition()) ? RecruitRole.FRONTEND : RecruitRole.BACKEND;;

        RecruitApplyEntity recruitApply = RecruitApplyEntity.builder()
                .groupPost(groupPost)
                .user(user)
                .position(position)
                .email(request.getEmail())
                .applicationReason(request.getApplicationReason())
                .introduction(request.getIntroduction())
                .techStack(request.getTechStack())
                .portfolioLink(request.getPortfolioLink())
                .availableDays(request.getAvailableDays())
                .additionalNotes(request.getAdditionalNotes())
                .build();

        recruitApplyRepository.save(recruitApply);
    }

    public Page<GroupPostEntity> findGroupPostByUserIdAndType(Long userId, Pageable pageable, GroupType type) {
        return recruitApplyRepository.findGroupPostByUserIdAndType(userId, pageable, type);
    }

    public List<RecruitApplyEntity> getPendingApplicationsByGroupId(Long groupId) {
        return recruitApplyRepository.findPendingApplicationsByGroupId(groupId);
    }
}
