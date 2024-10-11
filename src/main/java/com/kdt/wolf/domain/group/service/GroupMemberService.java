package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dto.request.EvaluateRequest;
import com.kdt.wolf.domain.group.dto.response.GroupMemberResponse;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    private final GroupMemberDao groupMemberDao;

    public List<GroupMemberResponse> getGroupMembers(Long groupId) {
        List<GroupMemberEntity> memberEntities = groupMemberDao.findAllByGroupId(groupId);
        return memberEntities.stream()
                .map(GroupMemberResponse::new)
                .toList();
    }

    public void addEvaluation(Long groupId, List<EvaluateRequest> requests) {
        for (EvaluateRequest request : requests) {
            GroupMemberEntity member = groupMemberDao.findByGroupIdAndMemberId(groupId, request.getMemberId());
            groupMemberDao.addEvaluation(member, request);
        }
    }
}
