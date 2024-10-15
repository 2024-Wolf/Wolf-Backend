package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoardEntity, Long> {

    @Query("SELECT q FROM QuestionBoardEntity q WHERE q.groupPost.groupPostId = :groupId AND q.boardType = :boardType")
    Page<QuestionBoardEntity> findByGroupPostIdAndBoardType(Long groupId, BoardType boardType, Pageable pageable);

}
