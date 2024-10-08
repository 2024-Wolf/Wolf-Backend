package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class GroupMemberResponse {
    private final MemberRole role;
    private final String position;

    public GroupMemberResponse(GroupMemberEntity groupMember) {
        this.role = groupMember.getRole();
        this.position = groupMember.getPosition();
    }
}
