package com.kdt.wolf.domain.notice.entity;

import com.kdt.wolf.domain.notice.dto.request.NoticeRequestDto;
import com.kdt.wolf.domain.user.entity.UserEntity; // UserEntity 임포트
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private LocalDate noticePostedDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(length = 1)
    // N: 비활성화, Y: 활성화 상태
    private char noticeIsActive;

    @Builder
    public NoticeEntity(String noticeTitle, String noticeContent, String noticeThumbnail, char noticeIsActive, UserEntity user) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeThumbnail = noticeThumbnail;
        this.noticeIsActive = noticeIsActive;
        this.user = user; // UserEntity 초기화

    }
}

