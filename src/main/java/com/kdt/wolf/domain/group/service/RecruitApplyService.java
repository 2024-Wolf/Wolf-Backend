package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.RecruitApplyDao;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitApplyService {
    private final RecruitApplyDao recruitApplyDao;

    public RecruitApplyEntity getApplicationsById(Long recruitApplyId) {
        return recruitApplyDao.getById(recruitApplyId);
    }


    public void recruitApply(Long postId, Long userId, RecruitApplyRequest request){
        recruitApplyDao.applyToGroup(postId, userId, request);
    }

}
