package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitApplyRepository extends JpaRepository<RecruitApplyEntity, Long> {
    @Query("SELECT r.groupPost "
            + "FROM RecruitApplyEntity r "
            + "WHERE r.user.userId = :userId AND r.groupPost.type = :type")
    Page<GroupPostEntity> findGroupPostByUserIdAndType(Long userId, Pageable pageable, GroupType type);
}
