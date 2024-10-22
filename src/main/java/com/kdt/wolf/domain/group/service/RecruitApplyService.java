package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.RecruitApplyDao;
import com.kdt.wolf.domain.group.dao.RecruitmentsDao;
import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.global.dto.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitApplyService {
    private final RecruitApplyDao recruitApplyDao;
    private final RecruitmentsDao recruitmentsDao;

    public RecruitApplyEntity getApplicationsById(Long recruitApplyId) {
        return recruitApplyDao.getById(recruitApplyId);
    }


    public void recruitApply(Long postId, Long userId, RecruitApplyRequest request){
        recruitApplyDao.applyToGroup(postId, userId, request);
    }

    public GroupPostPageResponse getAppliedGroupsByUserIdAndType(Long userId, GroupType type, Pageable pageable) {
        Page<GroupPostEntity> posts = recruitApplyDao.findGroupPostByUserIdAndType(userId, pageable, type);

        if(posts.isEmpty()) {
            return new GroupPostPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new GroupPostPageResponse(
                posts.getContent().stream().map(
                        post -> new GroupPostResponse(
                                post,
                                recruitmentsDao.findByGroupPost(post).stream()
                                        .map(recruitment -> new Recruitments(
                                                recruitment.getRecruitRole(),
                                                recruitment.getRecruitRoleCnt()
                                        )).toList()
                        )).toList(),
                new PageResponse(posts)
        );
    }
}
