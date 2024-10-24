package com.kdt.wolf.domain.link.repository;

import com.kdt.wolf.domain.group.entity.common.LinkType;
import com.kdt.wolf.domain.link.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExternalLinksRepository extends JpaRepository<ExternalLinksEntity, Long> {
    List<ExternalLinksEntity> findALLByGroupPost(GroupPostEntity groupPost);

//    @Query("SELECT e FROM ExternalLinksEntity e WHERE e.user.userId = :user")
    List<ExternalLinksEntity> findALLByUser(UserEntity user);

    boolean existsByGroupPostAndLinkType(GroupPostEntity groupPost, LinkType linkType);
}
