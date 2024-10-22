package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupNewsRepository extends JpaRepository<GroupNewsEntity, Long> {
}
