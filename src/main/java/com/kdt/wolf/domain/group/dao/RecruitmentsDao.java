package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.group.repository.RecruitmentsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecruitmentsDao {
    private final RecruitmentsRepository recruitmentsRepository;

    public RecruitmentsEntity save(RecruitmentsEntity recruitments) {
        return recruitmentsRepository.save(recruitments);

    }

    public List<RecruitmentsEntity> findByGroupPost(GroupPostEntity groupPost) {
        return recruitmentsRepository.findByGroupPost(groupPost);
    }
}
