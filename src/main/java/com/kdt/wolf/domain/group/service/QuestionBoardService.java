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
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import com.kdt.wolf.global.service.S3FileService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class QuestionBoardService {
    private final QuestionBoardDao questionBoardDao;
    private final UserDao userDao;
    private final S3FileService s3FileService;

    public QuestionPageResponse getQuestions(Long groupPostId, String option, Pageable pageable) {
        Page<QuestionBoardEntity> questions = Page.empty();
        if(option.trim().equalsIgnoreCase("question")) {
            questions = questionBoardDao.getQuestions(groupPostId, BoardType.QUESTION, pageable);
        } else if (option.trim().equalsIgnoreCase("communication")) {
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

    public Long insertQuestion(Long groupPostId, String option, QuestionRequest request, Long userId) {
        UserEntity user = findUserById(userId);

        return questionBoardDao.createQuestion(groupPostId, option, request, user)
                .getQuestionId();
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

    @Transactional
    public String uploadQuestionImage(Long questionId, MultipartFile questionImage) {
        QuestionBoardEntity question = questionBoardDao.findQuestionById(questionId);
        String responseUrl = uploadProfileImage(questionId, questionImage);
        question.updateQuestionImage(responseUrl);

        String deleteImageUrl = question.getQuestionImageUrl();
        if(deleteImageUrl != null && deleteImageUrl.contains("s3.amazonaws.com")) {
            s3FileService.delete(deleteImageUrl);
        }

        return responseUrl;
    }
    private String uploadProfileImage(Long questionId, MultipartFile profileImg) {
        String responseUrl;
        try {
            String path = "question"  + "/" + questionId;
            responseUrl = s3FileService.upload(profileImg, path);
        } catch (IOException e) {
            throw new BusinessException(ExceptionCode.PROFILE_IMAGE_UPLOAD_FAIL);
        }
        return responseUrl;
    }
}

