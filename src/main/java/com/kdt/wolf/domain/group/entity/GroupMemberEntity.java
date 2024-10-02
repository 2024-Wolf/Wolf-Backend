package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_member")
public class GroupMemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group_member_id")
    @SequenceGenerator(name = "seq_group_member_id", sequenceName = "group_member_sequence", allocationSize = 1)
    private Long groupMemberId;

    @ManyToOne
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(length = 100)
    private String position;

    @Builder
    public GroupMemberEntity(GroupPostEntity groupPost, UserEntity user, MemberRole role,
                             String position) {
        this.groupPost = groupPost;
        this.user = user;
        this.role = role;
        this.position = position;
    }

}