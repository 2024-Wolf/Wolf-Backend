package com.kdt.wolf.domain.notice.service;


import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.dao.NoticeDao;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeResponseDto;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;

    public List<NoticeResponseDto> getNotices() {
        List<NoticeEntity> notices = noticeDao.findAll();
        return notices.stream()
                .filter(NoticeEntity::isActive)
                .map(NoticeResponseDto::new)
                .toList();
    }

    public List<NoticePreviewDto> getNoticePreviews() {
        List<NoticeEntity> notices = noticeDao.findAll();
        return notices.stream()
                .map(NoticePreviewDto::new)
                .toList();
    }

    public NoticeDetailDto getNotice(Long noticeId) {
        NoticeEntity notice = noticeDao.findById(noticeId);
        return new NoticeDetailDto(notice);
    }
}

