package com.kdt.wolf.domain.notice.entity;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.user.entity.UserEntity; // UserEntity 임포트
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notice")
public class NoticeEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notice_id")
    @SequenceGenerator(name = "seq_notice_id", sequenceName = "notice_sequence", allocationSize = 1)
    private Long noticeId;

    @Column(nullable = false, length = 255)
    private String noticeTitle;

    @Column(nullable = false)
    private String noticeContent;

    @Column(nullable = true, length = 255)
    private String noticeThumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminEntity admin;

    @Column(columnDefinition = "CHAR(1) DEFAULT '0' CHECK(notice_is_active IN ('0', '1'))")
    private boolean isActive = false; // false: 비활성화, true: 활성화

    @Builder
    public NoticeEntity(String noticeTitle, String noticeContent, String noticeThumbnail, AdminEntity admin) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeThumbnail = noticeThumbnail;
        this.admin = admin;
    }

    public boolean changeActive(boolean isActive) {
        return this.isActive = isActive;
    }
}

