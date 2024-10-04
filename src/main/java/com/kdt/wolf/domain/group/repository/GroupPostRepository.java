package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupPostRepository extends JpaRepository<GroupPostEntity, Long> {
    Optional<GroupPostEntity> findByGroupPostId(Long groupPostId);
    List<GroupPostEntity> findByType(GroupType type);

    @Query("SELECT g FROM GroupPostEntity g WHERE g.title LIKE %:keyword% OR g.description LIKE %:keyword% OR g.tag LIKE %:keyword% OR g.name LIKE %:keyword%")
    List<GroupPostEntity> findByKeyword(String keyword);
}
