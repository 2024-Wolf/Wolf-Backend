package com.kdt.wolf.domain.notice.dto;

import com.kdt.wolf.domain.notice.dao.NoticeAdminDto;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.global.dto.PageResponse;

import java.time.LocalDateTime;
import java.util.List;

public class NoticeDto {
    public record NoticeResponseDto (
        Long noticeId,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        public NoticeResponseDto(NoticeEntity entity) {
            this(
                entity.getNoticeId(),
                entity.getNoticeTitle(),
                entity.getNoticeContent(),
                entity.getAdmin().getNickname(),
                entity.getCreatedTime(),
                entity.getModifiedTime()
            );
        }
    }
    public record NoticePageResponseDto (
            List<NoticeResponseDto> notices,
            PageResponse page
    ) {}
}
