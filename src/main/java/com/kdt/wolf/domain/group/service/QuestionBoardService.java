package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.QuestionBoardDao;
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

}

