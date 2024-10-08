package com.kdt.wolf.domain.notice.dto.request;

import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.*;


import java.time.LocalDate;

//Entity 클래스는 절대로 요청(Request)에 사용되어서는 안 된다는 말씀을 드렸고,
//이러한 이유로 BoardRequestDto로 전달받은 데이터(파라미터)를 기준으로 Entity 객체를 생성합니다.

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