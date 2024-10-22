package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitmentsRepository extends JpaRepository<RecruitmentsEntity, Long> {
    @Query("SELECT r FROM RecruitmentsEntity r WHERE r.groupPost = :groupPost")
    List<RecruitmentsEntity> findByGroupPost(GroupPostEntity groupPost);
}
