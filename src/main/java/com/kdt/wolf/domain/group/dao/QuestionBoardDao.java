package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.group.repository.QuestionBoardRepository;
import com.kdt.wolf.domain.group.repository.QuestionCommentRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionBoardDao {
    private final QuestionBoardRepository questionBoardRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final GroupPostDao groupPostDao;

    public List<QuestionResponse> getQuestionsList(Long groupId, BoardType option) {
        List<QuestionBoardEntity> questionEntities = questionBoardRepository.findByGroupPostIdAndBoardType(groupId, option);
        if (questionEntities == null || questionEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return questionEntities.stream()
                .map(question -> {
                    List<QuestionCommentEntity> comments = questionCommentRepository.findByQuestionQuestionId(question.getQuestionId());
                    if (comments == null) {
                        comments = new ArrayList<>();
                    }
                    return new QuestionResponse(question, comments);
                })
                .toList();
    }

    public void createQuestion(Long groupId,String option, QuestionRequest request) {
        QuestionBoardEntity question = QuestionBoardEntity.builder()
                .groupPost(groupPostDao.findById(groupId))
                .user(request.getUser())
                .boardType(BoardType.valueOf(option.toUpperCase()))
                .questionTime(request.getQuestionTime())
                .questionDetails(request.getQuestionDetails())
                .questionImageUrl(request.getQuestionImageUrl())
                .build();

        questionBoardRepository.save(question);
    }

    public void updateQuestion(Long questionId, QuestionRequest request) {
        QuestionBoardEntity originalQuestion = questionBoardRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);

        originalQuestion.updateQuestion(request);
        questionBoardRepository.save(originalQuestion);
    }


    public void deleteById(Long questionId) {
        if (!questionBoardRepository.existsById(questionId)) {
            throw new NotFoundException();
        }
        questionBoardRepository.deleteById(questionId);
    }
}
