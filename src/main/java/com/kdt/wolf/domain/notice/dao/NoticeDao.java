package com.kdt.wolf.domain.notice.dao;

import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeDao {
    private final NoticeRepository noticeRepository;

    // 공지 목록 불러오기
    public List<NoticeEntity> findAll() {
        return noticeRepository.findAll();
    }


}