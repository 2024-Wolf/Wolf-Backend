package com.kdt.wolf.domain.group.repository;

import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCommentRepository extends JpaRepository<QuestionCommentEntity, Long> {
    List<QuestionCommentEntity> findByQuestionQuestionId(Long questionId);
}
