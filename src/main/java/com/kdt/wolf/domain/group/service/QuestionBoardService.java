package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.QuestionBoardDao;
import com.kdt.wolf.domain.group.dto.request.QuestionCommentRequest;
import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionPageResponse;
import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionBoardService {
    private final QuestionBoardDao questionBoardDao;
    private final UserDao userDao;

    public QuestionPageResponse getQuestions(Long groupPostId, String option, Pageable pageable) {
        Page<QuestionBoardEntity> questions = Page.empty();
        if(option.equals("question")) {
            questions = questionBoardDao.getQuestions(groupPostId, BoardType.QUESTION, pageable);
        } else if (option.equals("communication")) {
            questions =  questionBoardDao.getQuestions(groupPostId, BoardType.COMMUNICATION, pageable);
        }
        
        if(questions.isEmpty()) {
            return new QuestionPageResponse(List.of(), new PageResponse(Page.empty()));
        }
        return new QuestionPageResponse(
                questions.stream().map( question -> {
                    List<QuestionCommentEntity> comments = questionBoardDao.getComments(question.getQuestionId());
                    return new QuestionResponse(question, comments);
                }).toList(),
                new PageResponse(questions)
        );
    }

    public void insertQuestion(Long groupPostId, String option, QuestionRequest request, Long userId) {
        UserEntity user = findUserById(userId);

        questionBoardDao.createQuestion(groupPostId, option, request, user);
    }

    public void editQuestion(Long questionId, QuestionRequest updateRequest, Long userId) {
        questionBoardDao.updateQuestion(questionId, updateRequest, userId);
    }

    public void deleteQuestion(Long groupId, Long questionId) {
        questionBoardDao.deleteById(questionId);
    }


    public void createComment(Long questionId, QuestionCommentRequest request, Long userId) {
        UserEntity user = findUserById(userId);
        questionBoardDao.createComment(questionId, request, user);
    }

    public void createComment(Long questionId, Long commentId, QuestionCommentRequest request, Long userId) {
        // questionId와 commentId에 대한 댓글 생성
        UserEntity user = findUserById(userId);
        questionBoardDao.createComment(questionId, commentId, request, user);
    }

    public void editComment(Long commentId, QuestionCommentRequest request) {
        questionBoardDao.updateComment(commentId, request);
    }

    public void deleteComment(Long commentId) {
        questionBoardDao.deleteCommentById(commentId);
    }

    private UserEntity findUserById(Long userId) {
        return userDao.findById(userId);
    }
}

