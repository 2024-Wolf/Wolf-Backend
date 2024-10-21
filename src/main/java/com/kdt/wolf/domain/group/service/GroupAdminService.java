package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewPageResponse;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupAdminService {

    private final GroupPostDao groupPostDao;
    private final GroupMemberDao groupMemberDao;
    public GroupPreviewPageResponse getPosts(Pageable pageable) {

        Page<GroupPostEntity> posts = groupPostDao.findAll(pageable);

        if(posts.isEmpty()) {
            return new GroupPreviewPageResponse(List.of(), 0, 0, 0);
        }

        return new GroupPreviewPageResponse(
                posts.stream()
                        .map(post -> new GroupPreviewResponse(
                                post.getGroupPostId(),
                                post.getName(),
                                post.getType().name(),
                                post.getStartDate().toString(),
                                post.getEndDate().toString(),
                                groupMemberDao.countByGroupPostId(post.getGroupPostId()).intValue(),
                                post.getChallengeStatus()
                        )).toList(),
                posts.getNumber(),
                posts.getTotalPages(),
                posts.getNumber()
        );
    }
}
