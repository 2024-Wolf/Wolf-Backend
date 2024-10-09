package com.kdt.wolf.domain.notice.dto.request;

import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.*;


import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NoticeRequestDto {
    @NonNull
    private String noticeTitle;
    @NonNull
    private String noticeContent;

    private String noticeThumbnail;

    private UserEntity user;

    @NonNull
    private LocalDate noticePostedDate;

    @NonNull
    private char noticeIsActive;

    @Builder
    public NoticeRequestDto(String noticeTitle, String noticeContent, String noticeThumbnail, UserEntity user, LocalDate noticePostedDate, char noticeIsActive ) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeThumbnail = noticeThumbnail;
        this.user = user;
        this.noticePostedDate = noticePostedDate;
        this.noticeIsActive = noticeIsActive;
    }

}