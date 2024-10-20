package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.notice.entity.NoticeEntity;
import com.kdt.wolf.domain.notice.repository.NoticeRepository;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")  // 개발 환경에서만 실행
@RequiredArgsConstructor
public class NoticeDataInitializer implements CommandLineRunner {
    private final NoticeRepository noticeRepository;
    private final AdminRepository adminRepository; // AdminRepository 추가

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (noticeRepository.count() > 0) return;
        // Admin 데이터가 없을 경우 생성
        AdminEntity admin = adminRepository.save(AdminEntity.builder()
                .email("admin2@example.com")
                .password("password2")
                .nickname("admin2")
                .name("Admin Two")
                .build());
        insertNoticeData(admin);
        System.out.println("Notice 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    public void insertNoticeData(AdminEntity admin) {
        AdminEntity adminEntity = adminRepository.findById(admin.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + admin.getAdminId()));

        List<NoticeEntity> notices = Arrays.asList(
                NoticeEntity.builder()
                        .noticeTitle("공지사항 1")
                        .noticeContent("첫 번째 공지사항 내용")
                        .noticeThumbnail("thumb1.png")
                        .admin(adminEntity)
                        .build(),
                NoticeEntity.builder()
                        .noticeTitle("공지사항 2")
                        .noticeContent("두 번째 공지사항 내용")
                        .noticeThumbnail("thumb2.png")
                        .admin(adminEntity)
                        .build(),
                NoticeEntity.builder()
                        .noticeTitle("공지사항 3")
                        .noticeContent("세 번째 공지사항 내용")
                        .noticeThumbnail("thumb3.png")
                        .admin(adminEntity)
                        .build(),
                NoticeEntity.builder()
                        .noticeTitle("공지사항 4")
                        .noticeContent("네 번째 공지사항 내용")
                        .noticeThumbnail("thumb4.png")
                        .admin(adminEntity)
                        .build()
        );

        noticeRepository.saveAll(notices); // notices 리스트를 데이터베이스에 저장
    }
}
