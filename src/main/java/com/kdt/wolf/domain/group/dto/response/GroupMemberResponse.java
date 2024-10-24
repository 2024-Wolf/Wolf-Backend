package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class GroupMemberResponse {
    private final Long id;
    private final GroupUser groupUser;
    private final MemberRole role;
    private final String position;

    public GroupMemberResponse(GroupMemberEntity groupMember) {
        this.id = groupMember.getGroupMemberId();
        this.groupUser = new GroupUser(groupMember.getUser());
        this.role = groupMember.getRole();
        this.position = groupMember.getPosition();
    }

    @Getter
    public static class GroupUser{
        private final Long userId;
        private final String userNickname;
        private final String userProfileImg;

        public GroupUser(UserEntity userEntity) {
            this.userId = userEntity.getUserId();
            this.userNickname = userEntity.getNickname();
            this.userProfileImg = userEntity.getProfilePicture();
        }
    }
}
