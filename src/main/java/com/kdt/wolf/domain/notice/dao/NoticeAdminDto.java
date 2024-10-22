package com.kdt.wolf.domain.notice.dao;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.global.dto.PageResponse;

import java.time.LocalDateTime;
import java.util.List;

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
                entity.getAdmin().getNickname(),
                entity.getCreatedTime()
            );
        }
    }
    public record NoticePreviewPageResponse(
            List<NoticePreviewDto> notices,
            PageResponse page
    ) { }

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
                entity.getAdmin().getNickname(),
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
