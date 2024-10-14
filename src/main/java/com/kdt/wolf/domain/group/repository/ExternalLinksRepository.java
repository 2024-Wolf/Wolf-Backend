package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExternalLinksRepository extends JpaRepository<ExternalLinksEntity, Long> {
    List<ExternalLinksEntity> findALLByGroupPost(GroupPostEntity groupPost);
}
