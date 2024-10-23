package com.kdt.wolf.domain.notice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeCreateDto;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeDetailDtoByAdmin;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.dao.NoticeDao;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticePreviewPageResponse;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


class NoticeServiceTest {
    @Mock
    private NoticeDao noticeDao;
    @Mock
    private AdminDao adminDao;

    @InjectMocks
    private NoticeService noticeService;

    private NoticeEntity noticeEntity;
    private AdminEntity adminEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        adminEntity = AdminEntity.builder()
                .email("adminEmail")
                .password("adminPassword")
                .nickname("adminNick")
                .name("adminName")
                .build();

        noticeEntity = NoticeEntity.builder()
                .noticeTitle("Test Notice Title")
                .noticeContent("Test Notice Content")
                .admin(adminEntity)
                .build();

    }

    @Test
    void getNoticePreviews_Success() {
        // given
        Pageable pageable = Pageable.ofSize(20);
        when(noticeDao.findAll(pageable)).thenReturn(new PageImpl<>(List.of(noticeEntity)));

        // when
        NoticePreviewPageResponse result = noticeService.getNoticePreviews(pageable);

        // then
        assertNotNull(result);
        assertEquals(1, result.notices().size());

        NoticePreviewDto noticePreviewDto = result.notices().get(0);
        assertEquals("Test Notice Title", noticePreviewDto.title());
        assertEquals("adminNick", noticePreviewDto.author());

        verify(noticeDao, times(1)).findAll(pageable);
    }

    @Test
    void getNoticeDetail_Success() {
        // given
        when(noticeDao.findById(1L)).thenReturn(noticeEntity);

        // when
        NoticeDetailDtoByAdmin result = noticeService.getNoticeByAdmin(1L);

        // then
        assertNotNull(result);
        assertEquals("Test Notice Title", result.title());
        assertEquals("Test Notice Content", result.content());
        assertEquals("adminNick", result.author());

        verify(noticeDao, times(1)).findById(1L);
    }

    @Test
    void getNoticeDetail_Fail() {
        // given
        when(noticeDao.findById(999L)).thenThrow(new NotFoundException(ExceptionCode.NOT_FOUND_NOTICE));
        // then
        assertThrows(NotFoundException.class, () -> noticeService.getNoticeByAdmin(999L));

        verify(noticeDao, times(1)).findById(999L);
    }

    @Test
    void createNotice_Success() {
        NoticeCreateDto noticeCreateDto = new NoticeCreateDto("Test Title", "Test Content", "Test Thumbnail");
        Long adminId = adminEntity.getAdminId();

        when(adminDao.findById(adminId)).thenReturn(adminEntity);
        when(noticeDao.save(any(NoticeEntity.class))).thenReturn(1L);

        // when
        Long savedNoticeId = noticeService.createNotice(noticeCreateDto, adminId);

        // then
        assertNotNull(savedNoticeId);
        assertEquals(1L, savedNoticeId);

        verify(adminDao, times(1)).findById(adminId);
        verify(noticeDao, times(1)).save(any(NoticeEntity.class));
    }

    @Test
    void updateNotice_Success() {
        NoticeCreateDto noticeCreateDto = new NoticeCreateDto("New Title", "New Content", "New Thumbnail");
        Long noticeId = 1L;

        // Mock: noticeDao.findById()가 기존 NoticeEntity를 반환하도록 설정
        when(noticeDao.findById(noticeId)).thenReturn(noticeEntity);

        // when
        noticeService.updateNotice(noticeId, noticeCreateDto);

        // then
        assertEquals("New Title", noticeEntity.getNoticeTitle());
        assertEquals("New Content", noticeEntity.getNoticeContent());
        assertEquals("New Thumbnail", noticeEntity.getNoticeThumbnail());

        verify(noticeDao, times(1)).findById(noticeId);
    }

    @Test
    void deleteNotice_Success() {
        Long noticeId = 1L;
        when(noticeDao.findById(noticeId)).thenReturn(noticeEntity);

        noticeService.deleteNotice(noticeId);

        verify(noticeDao, times(1)).findById(noticeId);
        verify(noticeDao, times(1)).delete(noticeEntity);
    }
}