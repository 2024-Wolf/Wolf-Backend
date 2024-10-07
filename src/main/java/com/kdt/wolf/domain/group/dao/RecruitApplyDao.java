package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.RecruitRoleEntity;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.group.repository.RecruitApplyRepository;
import com.kdt.wolf.domain.group.repository.RecruitRoleRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecruitApplyDao {
    private final GroupPostRepository groupPostRepository;
    private final UserRepository userRepository;
    private final RecruitApplyRepository recruitApplyRepository;
    private final RecruitRoleRepository recruitRoleRepository;

    public RecruitApplyEntity getById(Long recruitApplyId) {
        return recruitApplyRepository.findById(recruitApplyId)
                .orElseThrow(NotFoundException::new);
    }

    public void applyToGroup(Long postId, Long userId, RecruitApplyRequest request) {
        GroupPostEntity groupPost = groupPostRepository.findById(postId)
                .orElseThrow(NotFoundException::new);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        RecruitRoleEntity position = recruitRoleRepository.findById(request.getPosition())
                .orElseThrow(NotFoundException::new);

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
}
