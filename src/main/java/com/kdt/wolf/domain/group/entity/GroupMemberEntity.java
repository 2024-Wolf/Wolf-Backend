package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_members")
public class GroupMemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_member_id")
    private Long groupMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_post_id", referencedColumnName = "id", nullable = false)
    private GroupPost groupPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

    @Column(name = "role", length = 50, nullable = false)
    private String role;

    @Column(name = "position", length = 100, nullable = true)
    private String position;

    @Builder
    public GroupMemberEntity(GroupPost groupPost, UserEntity user, String role, String position) {
        this.groupPost = groupPost;
        this.user = user;
        this.role = role;
        this.position = position;
    }

    public void updateRole(String role) {
        this.role = role;
    }

    public void updatePosition(String position) {
        this.position = position;
    }
}
