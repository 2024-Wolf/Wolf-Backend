package com.kdt.wolf.domain.notice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.dao.NoticeDao;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class NoticeServiceTest {
    @Mock
    private NoticeDao noticeDao;

    @InjectMocks
    private NoticeService noticeService;

    private NoticeEntity noticeEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AdminEntity adminEntity = AdminEntity.builder()
                .adminEmail("adminEmail")
                .adminPassword("adminPassword")
                .adminNickname("adminNick")
                .adminName("adminName")
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
        when(noticeDao.findAll()).thenReturn(List.of(noticeEntity));

        // when
        List<NoticePreviewDto> result = noticeService.getNoticePreviews();

        // then
        assertNotNull(result);
        assertEquals(1, result.size());

        NoticePreviewDto noticePreviewDto = result.get(0);
        assertEquals("Test Notice Title", noticePreviewDto.title());
        assertEquals("adminNick", noticePreviewDto.author());

        verify(noticeDao, times(1)).findAll();
    }

    @Test
    void getNoticeDetail_Success() {
        // given
        when(noticeDao.findById(1L)).thenReturn(noticeEntity);

        // when
        NoticeDetailDto result = noticeService.getNotice(1L);

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
        assertThrows(NotFoundException.class, () -> noticeService.getNotice(999L));

        verify(noticeDao, times(1)).findById(999L);
    }

}