package com.kdt.wolf.domain.notice.dao;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import java.time.LocalDateTime;

public class NoticeAdminDto {
    public record NoticePreviewDto(
        Long noticeId,
        String title,
        String author,
        LocalDateTime createdAt
    ) {
        public NoticePreviewDto(NoticeEntity entity) {
            this(
                entity.getNoticeId(),
                entity.getNoticeTitle(),
                entity.getAdmin().getAdminNickname(),
                entity.getCreatedTime()
            );
        }
    }

    public record NoticeDetailDto(
        Long noticeId,
        String title,
        String content,
        String thumbnail,
        String author,
        LocalDateTime createdAt,
        Boolean isActive
    ) {
        public NoticeDetailDto(NoticeEntity entity) {
            this(
                entity.getNoticeId(),
                entity.getNoticeTitle(),
                entity.getNoticeContent(),
                entity.getNoticeThumbnail(),
                entity.getAdmin().getAdminNickname(),
                entity.getCreatedTime(),
                entity.isActive()
            );
        }
    }

    public record NoticeCreateDto(
        String title,
        String content,
        String thumbnail
    ) { }
}
