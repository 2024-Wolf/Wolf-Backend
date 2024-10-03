package com.kdt.wolf.domain.notice.entity;

import com.kdt.wolf.domain.faq.entity.FaqCategoriesEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "notice")
public class NoticeEntity extends BaseTimeEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notice_id")
//    @SequenceGenerator(name = "seq_notice_id", sequenceName = "notice_sequence", allocationSize = 1)
    private Long noticeId;

    @Column(nullable = false, length = 255)
    private String noticeTitle;

    @Column(nullable = false)
    private String noticeContent;

    @Column(nullable = true, length = 255)
    private String noticeThumbnail;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FaqCategoriesEntity categoryId;

    @Column(nullable = false)
    private LocalDate noticePostedDate;

    @Column(nullable = false)
    private char noticeIsActive;


    @Builder
    public NoticeEntity(String noticeTitle, String noticeContent, String noticeThumbnail, char noticeIsActive) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeThumbnail = noticeThumbnail;
        this.noticePostedDate = LocalDate.now();
        this.noticeIsActive = noticeIsActive;
    }
}

