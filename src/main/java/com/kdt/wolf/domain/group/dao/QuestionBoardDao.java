package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.QuestionCommentRequest;
import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionPageResponse;
import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.group.repository.QuestionBoardRepository;
import com.kdt.wolf.domain.group.repository.QuestionCommentRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionBoardDao {
    private final QuestionBoardRepository questionBoardRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final GroupPostDao groupPostDao;

    public Page<QuestionBoardEntity> getQuestions(Long groupPostId, BoardType boardType, Pageable pageable) {
        return questionBoardRepository.findByGroupPostIdAndBoardType(groupPostId, boardType, pageable);
    }

    public List<QuestionCommentEntity> getComments(Long questionId) {
        return questionCommentRepository.findByQuestionQuestionId(questionId);
    }

    public QuestionBoardEntity createQuestion(Long groupId,String option, QuestionRequest request, UserEntity user) {
        QuestionBoardEntity question = QuestionBoardEntity.builder()
                .groupPost(groupPostDao.findById(groupId))
                .user(user)
                .boardType(BoardType.valueOf(option.toUpperCase()))
                .questionTime(request.getQuestionTime())
                .questionDetails(request.getQuestionDetails())
                .questionImageUrl(request.getQuestionImageUrl())
                .build();

        return questionBoardRepository.save(question);
    }
    @Transactional
    public void updateQuestion(Long questionId, QuestionRequest request, Long userId) {
        QuestionBoardEntity originalQuestion = questionBoardRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);
        if(!originalQuestion.getUser().getUserId().equals(userId)){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        originalQuestion.updateQuestion(request);
        questionBoardRepository.save(originalQuestion);
    }


    public void deleteById(Long questionId) {
        if (!questionBoardRepository.existsById(questionId)) {
            throw new NotFoundException();
        }
        questionBoardRepository.deleteById(questionId);
    }

    public void createComment(Long questionId, QuestionCommentRequest request, UserEntity user) {
        QuestionBoardEntity questionBoard = questionBoardRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);

        QuestionCommentEntity comment = QuestionCommentEntity.builder()
                .question(questionBoard)
                .author(user)
                .commentDetails(request.getCommentDetails())
                .commentImageUrl(request.getCommentImageUrl())
                .createTime(request.getCommentTime())
                .build();

        questionCommentRepository.save(comment);
    }

    public void createComment(Long questionId, Long commentId, QuestionCommentRequest request, UserEntity user) {
        QuestionBoardEntity questionBoard = questionBoardRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);
        QuestionCommentEntity parentComment = questionCommentRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        QuestionCommentEntity comment = QuestionCommentEntity.builder()
                .question(questionBoard)
                .parentComment(parentComment)
                .author(user)
                .commentDetails(request.getCommentDetails())
                .commentImageUrl(request.getCommentImageUrl())
                .createTime(request.getCommentTime())
                .build();

        questionCommentRepository.save(comment);
    }

    public void updateComment(Long commentId, QuestionCommentRequest request) {
        QuestionCommentEntity originalComment = questionCommentRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        originalComment.updateComment(request);
        questionCommentRepository.save(originalComment);
    }

    public void deleteCommentById(Long commentId) {
        if (!questionCommentRepository.existsById(commentId)) {
            throw new NotFoundException();
        }
        questionCommentRepository.deleteById(commentId);
    }

    public QuestionCommentEntity findCommentById(Long commentId) {
        return questionCommentRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);
    }

    public QuestionBoardEntity findQuestionById(Long questionId) {
        return questionBoardRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);
    }
}
