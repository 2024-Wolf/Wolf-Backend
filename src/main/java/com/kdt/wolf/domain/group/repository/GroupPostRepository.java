package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupPostRepository extends JpaRepository<GroupPostEntity, Long> {
    Page<GroupPostEntity> findByType(GroupType type, Pageable pageable);

    @Query("SELECT g FROM GroupPostEntity g WHERE g.topic LIKE %:keyword% OR g.description LIKE %:keyword% OR g.tag LIKE %:keyword% OR g.name LIKE %:keyword%")
    List<GroupPostEntity> findByKeyword(String keyword);

}
