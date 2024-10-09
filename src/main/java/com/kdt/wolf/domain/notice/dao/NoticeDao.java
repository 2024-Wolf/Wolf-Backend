package com.kdt.wolf.domain.notice.dao;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.domain.notice.repository.NoticeRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeDao {
    private final NoticeRepository noticeRepository;

    // 공지 목록 불러오기
    public List<NoticeEntity> findAll() {
        List<NoticeEntity> notices = noticeRepository.findAll();
        if(notices.isEmpty()) {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE);
        }
        return notices;
    }

    public NoticeEntity findById(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE));
    }
}