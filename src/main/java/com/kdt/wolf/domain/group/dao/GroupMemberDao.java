package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.repository.GroupMemberRepository;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupMemberDao {
    private final GroupMemberRepository groupMemberRepository;
    private final GroupPostRepository groupPostRepository;

    public List<GroupMemberEntity> findAllByGroupId(Long groupId) {
        GroupPostEntity post = groupPostRepository.findById(groupId).orElseThrow(NotFoundException::new);
        return groupMemberRepository.findAllByGroupPost(post);
    }
}
