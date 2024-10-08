package com.kdt.wolf.domain.notice.dto.response;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class NoticeResponseDto {
    private final Long noticeId;
    private final String noticeTitle;
    private final String noticeContent;
    private final String noticeThumbnail;
    private final UserEntity user;
    private final String noticePostedDate;
    private final char noticeIsActive;

    public NoticeResponseDto(NoticeEntity noticeEntity) {
        this.noticeId = noticeEntity.getNoticeId();
        this.noticeTitle = noticeEntity.getNoticeTitle();
        this.noticeContent = noticeEntity.getNoticeContent();
        this.noticeThumbnail = noticeEntity.getNoticeThumbnail();
        this.user = noticeEntity.getUser();
        this.noticePostedDate = noticeEntity.getNoticePostedDate().toString();
        this.noticeIsActive = noticeEntity.getNoticeIsActive();
    }


}
