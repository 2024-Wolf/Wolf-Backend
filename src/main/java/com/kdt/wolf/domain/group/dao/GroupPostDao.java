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

    public List<GroupPostEntity> findByType(String option) {
        return switch (option) {
            case "all" -> groupPostRepository.findAll();
            case "study" -> groupPostRepository.findByType(GroupType.STUDY);
            case "project" -> groupPostRepository.findByType(GroupType.PROJECT);
            default -> throw new NotFoundException();
        };
    }

    public List<GroupPostEntity> findByKeyword(String keyword) {
        return groupPostRepository.findByKeyword(keyword);
    }

    public void createPost(GroupPostRequest request) {
        UserEntity leaderUser = userRepository.findById(request.getLeaderUser().getUserId())
                .orElseThrow(UserNotFoundException::new);

        // GroupPostEntity 생성
        GroupPostEntity groupPost = GroupPostEntity.builder()
                .name(request.getName())
                .type(request.getType())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .recruitStartDate(request.getRecruitStartDate())
                .recruitDeadlineDate(request.getRecruitDeadlineDate())
                .shortIntro(request.getShortIntro())
                .tag(request.getTag())
                .optionalRequirements(request.getOptionalRequirements())
                .targetMembers(request.getTargetMembers())
                .thumbnail(request.getThumbnail())
                .title(request.getTitle())
                .description(request.getDescription())
                .warning(request.getWarning())
                .challengeStatus(request.getChallengeStatus())
                .leaderUser(leaderUser)
                .build();

        groupPostRepository.save(groupPost); // DB에 저장
    }

    public void updateGroupPost(Long groupPostId, GroupPostRequest request) {

        GroupPostEntity existingGroupPost = groupPostRepository.findById(groupPostId)
                .orElseThrow(NotFoundException::new);

        existingGroupPost.updateGroupPost(request);
        groupPostRepository.save(existingGroupPost);

    }
}
