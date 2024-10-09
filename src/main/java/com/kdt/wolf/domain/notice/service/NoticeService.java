package com.kdt.wolf.domain.notice.service;


import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeCreateDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.dao.NoticeDao;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeResponseDto;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;
    private final AdminDao adminDao;

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

    public Long createNotice(NoticeCreateDto notice, Long adminId) {
        AdminEntity admin = adminDao.findById(adminId);
        return noticeDao.save(NoticeEntity.builder()
                .noticeTitle(notice.title())
                .noticeContent(notice.content())
                .noticeThumbnail(notice.thumbnail())
                .admin(admin)
                .build()
        );
    }

    @Transactional
    public Long updateNotice(Long noticeId, NoticeCreateDto notice) {
        NoticeEntity noticeEntity = noticeDao.findById(noticeId);
        noticeEntity.updateNotice(notice.title(), notice.content(), notice.thumbnail());
        return noticeEntity.getNoticeId();
    }


}

