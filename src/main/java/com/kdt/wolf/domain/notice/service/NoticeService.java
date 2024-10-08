package com.kdt.wolf.domain.notice.service;


import com.kdt.wolf.domain.notice.dao.NoticeDao;
import com.kdt.wolf.domain.notice.dto.response.NoticeResponseDto;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //final로 선언된 모든 맴버 생성자 만들어줌.
public class NoticeService {

    private final NoticeDao noticeDao;

    //목록조회
    public List<NoticeResponseDto> getNotices() {
        List<NoticeEntity> notices = noticeDao.findAll();
        return notices.stream()
                .map(NoticeResponseDto::new)
                .toList();
    }
}

