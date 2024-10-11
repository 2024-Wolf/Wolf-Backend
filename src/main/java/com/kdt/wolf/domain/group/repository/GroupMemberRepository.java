package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, GroupPostEntity> {
    List<GroupMemberEntity> findAllByGroupPost(GroupPostEntity groupId); // 그룹 ID로 모임원 조회
    Optional<GroupMemberEntity> findByGroupPostAndGroupMemberId(GroupPostEntity group, Long memberId);
}
