package com.kdt.wolf.domain.link.entity;

import com.kdt.wolf.domain.link.dto.LinkRequest;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.common.LinkType;
import com.kdt.wolf.domain.user.dto.UserDto.UserLinkUpdateRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "external_links")
public class ExternalLinksEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_link_link_id")
    @SequenceGenerator(name = "seq_link_link_id", sequenceName = "link_sequence", allocationSize = 1)
    private Long linkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GroupPostEntity groupPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private LinkType linkType;

    private String linkUrl;

    @Builder(builderMethodName = "groupBuilder")
    public ExternalLinksEntity(GroupPostEntity groupPost, LinkType linkType, String linkUrl) {
        this.groupPost = groupPost;
        this.linkType = linkType;
        this.linkUrl = linkUrl;
    }

    @Builder(builderMethodName = "userBuilder")
    public ExternalLinksEntity(UserEntity user, LinkType linkType, String linkUrl) {
        this.user = user;
        this.linkType = linkType;
        this.linkUrl = linkUrl;
    }

    public void updateLink(LinkRequest request) {
        this.linkType = LinkType.valueOf(request.getLinkType().toUpperCase());
        this.linkUrl = request.getLinkUrl();
    }

    public void updateLink(UserLinkUpdateRequest request) {
        this.linkType = LinkType.valueOf(request.linkType().toUpperCase());
        this.linkUrl = request.linkUrl();
    }
}
