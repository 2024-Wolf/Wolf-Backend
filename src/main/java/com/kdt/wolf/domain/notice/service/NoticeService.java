package com.kdt.wolf.domain.notice.service;


import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewPageResponse;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeCreateDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.dao.NoticeDao;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticePageResponseDto;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeResponseDto;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.global.dto.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;
    private final AdminDao adminDao;

    public NoticePageResponseDto getNotices(Pageable pageable) {
        Page<NoticeEntity> notices = noticeDao.findAll(pageable);

        if(notices.isEmpty()) {
            return new NoticePageResponseDto(List.of(), new PageResponse(Page.empty()));
        }
        return new NoticePageResponseDto(
                notices.getContent().stream()
                        .map(NoticeResponseDto::new)
                        .toList(),
                new PageResponse(notices)
        );
    }

    public NoticePreviewPageResponse getNoticePreviews(Pageable pageable) {
        Page<NoticeEntity> notices = noticeDao.findAll(pageable);

        if(notices.isEmpty()) {
            return new NoticePreviewPageResponse(List.of(), new PageResponse(Page.empty()));
        }
        return new NoticePreviewPageResponse(
                notices.getContent().stream()
                        .map(NoticePreviewDto::new)
                        .toList(),
                new PageResponse(notices)
        );
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

    @Transactional
    public void deleteNotice(Long noticeId) {
        NoticeEntity notice = noticeDao.findById(noticeId);
        noticeDao.delete(notice);
    }

}

