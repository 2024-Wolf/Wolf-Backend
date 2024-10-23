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
                        .noticeTitle("나에게 맞는 스터디와 프로젝트를 찾아보세요")
                        .noticeContent("WOLF는 여러분의 성장을 위해 다양한 스터디와 프로젝트 기회를 제공합니다. 지금 바로 WOLF에서 당신에게 맞는 스터디와 프로젝트를 찾아보세요. 새롭게 추가된 기능을 통해 사용자 맞춤형 추천도 받을 수 있습니다. 학습과 성장을 동시에 이루는 기회를 놓치지 마세요!")
                        .noticeThumbnail("https://wolfbucket2024.s3.ap-northeast-2.amazonaws.com/profileImg/banner/banner1.png")
                        .admin(adminEntity)
                        .build(),

                NoticeEntity.builder()
                        .noticeTitle("SUPER CHALLENGE WEEK")
                        .noticeContent("2024.10.21~2024.10.25 동안 진행되는 슈퍼 챌린지 주간에 참여하세요! 이번 챌린지 기간 동안 다양한 도전 과제가 준비되어 있으며, 챌린지에 성공하면 특별한 보상도 드립니다. 모든 유저가 함께 성장할 수 있는 이 기회를 절대 놓치지 마세요! 지금 바로 참여하세요!")
                        .noticeThumbnail("https://wolfbucket2024.s3.ap-northeast-2.amazonaws.com/profileImg/banner/banner2.png")
                        .admin(adminEntity)
                        .build(),

                NoticeEntity.builder()
                        .noticeTitle("최대 200% 환급 이벤트")
                        .noticeContent("공부만 해도 선물을 받을 수 있는 절호의 기회! 2024년 마지막 환급 이벤트로 최대 200%의 환급 혜택을 누리세요. 공부한 만큼 돌려받고, 다양한 챌린지를 통해 추가 보상도 받을 수 있습니다. 이번 기회를 놓치지 마시고, WOLF에서 학습과 성과를 동시에 챙기세요!")
                        .noticeThumbnail("https://wolfbucket2024.s3.ap-northeast-2.amazonaws.com/profileImg/banner/banner3.png")
                        .admin(adminEntity)
                        .build(),

                NoticeEntity.builder()
                        .noticeTitle("WOLF에서 우리 함께 프로젝트할래?")
                        .noticeContent("WOLF에서 새로운 프로젝트 기회를 찾아보세요! 다양한 분야의 프로젝트가 준비되어 있으며, 참여를 통해 실력을 키울 수 있습니다. 팀과 협업하며 함께 목표를 달성하고 성장하는 기회를 경험하세요. 프로젝트를 통해 더 나은 당신을 만들 수 있는 이번 기회를 놓치지 마세요!")
                        .noticeThumbnail("https://wolfbucket2024.s3.ap-northeast-2.amazonaws.com/profileImg/banner/banner4.png")
                        .admin(adminEntity)
                        .build()
        );

        noticeRepository.saveAll(notices); // notices 리스트를 데이터베이스에 저장
    }
}
