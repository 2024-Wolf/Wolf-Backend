package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.QuestionBoardDao;
import com.kdt.wolf.domain.group.dto.request.QuestionCommentRequest;
import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionBoardService {
    private final QuestionBoardDao questionBoardDao;

    public List<QuestionResponse> getQuestionList(Long groupPostId, String option) {
        BoardType boardType = "question".equals(option) ? BoardType.QUESTION : BoardType.COMMUNICATION;
        return questionBoardDao.getQuestionsList(groupPostId, boardType);
    }

    public void insertQuestion(Long groupPostId, String option, QuestionRequest request) {
        questionBoardDao.createQuestion(groupPostId, option, request);
    }

    public void editQuestion(Long questionId, QuestionRequest updateRequest) {
        questionBoardDao.updateQuestion(questionId, updateRequest);
    }

    public void deleteQuestion(Long groupId, Long questionId) {
        questionBoardDao.deleteById(questionId);
    }


    public void createComment(Long questionId, QuestionCommentRequest request) {
        questionBoardDao.createComment(questionId, request);
    }

    public void createComment(Long questionId, Long commentId, QuestionCommentRequest request) {
        // questionId와 commentId에 대한 댓글 생성
        questionBoardDao.createComment(questionId, commentId, request);
    }

    public void editComment(Long commentId, QuestionCommentRequest request) {
        questionBoardDao.updateComment(commentId, request);
    }

    public void deleteComment(Long commentId) {
        questionBoardDao.deleteCommentById(commentId);
    }
}

