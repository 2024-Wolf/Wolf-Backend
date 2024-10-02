package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupPostRepository extends JpaRepository<GroupPostEntity, Long> {
    Optional<GroupPostEntity> findByGroupPostId(Long groupPostId);
    List<GroupPostEntity> findByType(GroupType type);
}
