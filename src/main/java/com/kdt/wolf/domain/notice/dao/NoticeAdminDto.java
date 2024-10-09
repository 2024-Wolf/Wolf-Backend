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
}
