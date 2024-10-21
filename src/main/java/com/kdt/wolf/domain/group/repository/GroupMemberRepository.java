package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.ChallengeParticipantMember;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Long> {
    List<GroupMemberEntity> findAllByGroupPost(GroupPostEntity groupId); // 그룹 ID로 모임원 조회
    Optional<GroupMemberEntity> findByGroupPostAndGroupMemberId(GroupPostEntity group, Long memberId);

    @Query("SELECT m.groupPost "
            + "FROM GroupMemberEntity m "
            + "WHERE m.user.userId = :userId AND m.groupPost.type = :type AND m.groupPost.endDate >= CURRENT_DATE"
    )
    Page<GroupPostEntity> findOngoingPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable);

    @Query("SELECT m.groupPost "
            + "FROM GroupMemberEntity m "
            + "WHERE m.user.userId = :userId AND m.groupPost.type = :type AND m.groupPost.endDate < CURRENT_DATE"
    )
    Page<GroupPostEntity> findCompletedPostsByUserIdAndType(Long userId, GroupType type, Pageable pageable);

    @Query("SELECT COUNT(m) "
            + "FROM GroupMemberEntity m "
            + "WHERE m.groupPost.groupPostId = :groupPostId"
    )
    Long countByGroupPostId(Long groupPostId);

    @Query("SELECT m "
            + "FROM GroupMemberEntity m "
            + "WHERE m.groupPost = :groupPost"
    )
    List<GroupMemberEntity> findGroupMembers(GroupPostEntity groupPost);
}