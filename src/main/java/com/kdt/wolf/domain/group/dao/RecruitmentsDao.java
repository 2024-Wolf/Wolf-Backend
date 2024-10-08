package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.group.repository.RecruitMentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecruitmentsDao {
    private final RecruitMentsRepository recruitMentsRepository;

    public RecruitmentsEntity save(RecruitmentsEntity recruitments) {
        return recruitMentsRepository.save(recruitments);

    }
}
