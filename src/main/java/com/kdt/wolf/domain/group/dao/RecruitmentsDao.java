package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.group.repository.RecruitmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecruitmentsDao {
    private final RecruitmentsRepository recruitmentsRepository;

    public RecruitmentsEntity save(RecruitmentsEntity recruitments) {
        return recruitmentsRepository.save(recruitments);

    }
}
