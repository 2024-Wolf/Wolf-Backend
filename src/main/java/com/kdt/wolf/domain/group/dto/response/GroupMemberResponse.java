package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class GroupMemberResponse {
    private final Long groupMemberId;
    private final GroupPostEntity groupPost;
    private final UserEntity user;
    private final String userNickname;
    private final MemberRole role;
    private final String position;

    public GroupMemberResponse(GroupMemberEntity groupMember) {
        this.groupMemberId = groupMember.getGroupMemberId();
        this.groupPost = groupMember.getGroupPost();
        this.user = groupMember.getUser();
        this.userNickname = user.getNickname();
        this.role = groupMember.getRole();
        this.position = groupMember.getPosition();
    }
}
