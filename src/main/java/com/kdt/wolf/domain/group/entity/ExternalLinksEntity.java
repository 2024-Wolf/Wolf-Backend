package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.group.dto.request.LinkRequest;
import com.kdt.wolf.domain.group.entity.common.LinkType;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "external_links")
public class ExternalLinksEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_link_link_id")
    @SequenceGenerator(name = "seq_link_link_id", sequenceName = "link_sequence", allocationSize = 1)
    private Long linkId;

    @ManyToOne
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private LinkType linkType;

    private String linkUrl;

    @Builder
    public ExternalLinksEntity(GroupPostEntity groupPost, UserEntity user, LinkType linkType, String linkUrl) {
        this.groupPost = groupPost;
        this.user = user;
        this.linkType = linkType;
        this.linkUrl = linkUrl;
    }

    public void updateLink(LinkRequest request) {
        this.linkType = LinkType.valueOf(request.getLinkType().toUpperCase());
        this.linkUrl = request.getLinkUrl();
    }
}
