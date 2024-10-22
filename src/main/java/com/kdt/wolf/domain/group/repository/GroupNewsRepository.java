package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupNewsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupNewsRepository extends JpaRepository<GroupNewsEntity, Long> {
    @Query("SELECT  gn FROM GroupNewsEntity gn WHERE gn.groupPost.groupPostId = :groupId")
    List<GroupNewsEntity> findByGroupId(Long groupId);
}
