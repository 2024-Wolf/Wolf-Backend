package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.group.repository.QuestionBoardRepository;
import com.kdt.wolf.domain.group.repository.QuestionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionBoardDao {
    private final QuestionBoardRepository questionBoardRepository;
    private final QuestionCommentRepository questionCommentRepository;

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

}
