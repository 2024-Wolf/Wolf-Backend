package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupPostDao {
    private final GroupPostRepository groupPostRepository;
    private final UserRepository userRepository;

    public GroupPostEntity findById(Long groupPostId) {
        return groupPostRepository.findById(groupPostId)
                .orElseThrow(NotFoundException::new);
    }

    public Page<GroupPostEntity> findByType(String option, Pageable pageable) {
        return switch (option) {
            case "all" -> groupPostRepository.findAll(pageable);
            case "study" -> groupPostRepository.findByType(GroupType.STUDY, pageable);
            case "project" -> groupPostRepository.findByType(GroupType.PROJECT, pageable);
            default -> throw new NotFoundException();
        };
    }

    public List<GroupPostEntity> findByKeyword(String keyword) {
        return groupPostRepository.findByKeyword(keyword);
    }

    public GroupPostEntity createPost(GroupPostRequest request) {
        UserEntity leaderUser = userRepository.findById(request.getLeaderUser().getUserId())
                .orElseThrow(UserNotFoundException::new);

        GroupType type = "study".equals(request.getType()) ? GroupType.STUDY : GroupType.PROJECT;

        // GroupPostEntity 생성
        GroupPostEntity groupPost = GroupPostEntity.builder()
                .name(request.getName())
                .type(type)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .recruitStartDate(request.getRecruitStartDate())
                .recruitDeadlineDate(request.getRecruitDeadlineDate())
                .shortIntro(request.getShortIntro())
                .tag(request.getTag())
                .optionalRequirements(request.getOptionalRequirements())
                .targetMembers(request.getTargetMembers())
                .thumbnail(request.getThumbnail())
                .topic(request.getTopic())
                .description(request.getDescription())
                .warning(request.getWarning())
                .challengeStatus(request.getChallengeStatus())
                .leaderUser(leaderUser)
                .build();

        return groupPostRepository.save(groupPost); // DB에 저장
    }

    public void updateGroupPost(Long groupPostId, GroupPostRequest request) {
        GroupPostEntity existingGroupPost = groupPostRepository.findById(groupPostId)
                .orElseThrow(NotFoundException::new);

        existingGroupPost.updateGroupPost(request);
        groupPostRepository.save(existingGroupPost);

    }

    public void deleteById(Long groupPostId) {
        if (!groupPostRepository.existsById(groupPostId)) {
            throw new NotFoundException();
        }
        groupPostRepository.deleteById(groupPostId);
    }

    public void delete(GroupPostEntity reportedGroupPost) {
        groupPostRepository.delete(reportedGroupPost);
    }
}
