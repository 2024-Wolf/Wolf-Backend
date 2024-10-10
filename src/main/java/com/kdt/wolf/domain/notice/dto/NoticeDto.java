package com.kdt.wolf.domain.notice.dto;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import java.time.LocalDateTime;

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
                entity.getAdmin().getAdminNickname(),
                entity.getCreatedTime(),
                entity.getModifiedTime()
            );
        }
    }
}
