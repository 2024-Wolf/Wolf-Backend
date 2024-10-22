package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitApplyRepository extends JpaRepository<RecruitApplyEntity, Long> {
    @Query("SELECT r "
            + "FROM RecruitApplyEntity r "
            + "WHERE r.user.userId = :userId AND r.groupPost.type = :type")
    Page<RecruitApplyEntity> findGroupPostByUserIdAndType(Long userId, Pageable pageable, GroupType type);

    @Query("SELECT r "
            + "FROM RecruitApplyEntity r "
            + "WHERE r.groupPost.groupPostId = :groupId AND r.applyStatus = 'PENDING'")
    List<RecruitApplyEntity> findPendingApplicationsByGroupId(Long groupId);
}
