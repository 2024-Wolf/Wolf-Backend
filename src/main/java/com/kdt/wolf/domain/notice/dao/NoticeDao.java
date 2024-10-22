package com.kdt.wolf.domain.notice.dao;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.domain.notice.repository.NoticeRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeDao {
    private final NoticeRepository noticeRepository;

    // 공지 목록 불러오기
    public Page<NoticeEntity> findAll(Pageable pageable) {

        return noticeRepository.findAll(pageable);
    }

    public NoticeEntity findById(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE));
    }

    public Long save(NoticeEntity noticeEntity) {
        return noticeRepository.save(noticeEntity).getNoticeId();
    }

    public void delete(NoticeEntity notice) {
        noticeRepository.delete(notice);
    }
}