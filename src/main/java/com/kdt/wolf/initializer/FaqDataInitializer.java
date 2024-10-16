package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.domain.faq.repository.FaqRepository;
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
public class FaqDataInitializer implements CommandLineRunner {
    private final AdminRepository adminRepository;
    private final FaqRepository faqRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (faqRepository.count() != 0) return;
        // Admin 데이터가 없을 경우 생성
        AdminEntity admin = adminRepository.save(AdminEntity.builder()
                .email("admin1@example.com")
                .password("password1")
                .nickname("admin1")
                .name("Admin One")
                .build());

        // FAQ 데이터 생성
        insertAccountFaqData(admin);
        insertStudyFaqData(admin);
        insertProjectFaqData(admin);
        insertChallengeFaqData(admin);
        insertEtcFaqData(admin);

        System.out.println("Admin 및 FAQ 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    protected void insertAccountFaqData(AdminEntity admin) {
        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("비회원으로 이용 가능한 기능이 있나요?")
                .answer("비회원은 검색과 조회만 가능합니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("회원정보 수정은 어디서 할 수 있나요?")
                .answer("회원정보 수정은 설정에서 가능합니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("탈퇴 신청을 철회할 수 있나요?")
                .answer("탈퇴 신청은 7일 이내에 철회 가능합니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("비밀번호를 잊어버렸습니다. 어떻게 하나요?")
                .answer("로그인 페이지에서 비밀번호 찾기를 이용해 주세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 보안은 어떻게 강화하나요?")
                .answer("2단계 인증을 설정하여 계정을 보호할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 잠금 해제 방법은?")
                .answer("고객센터에 문의하여 계정 잠금을 해제할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("가입 시 이메일 인증은 어떻게 하나요?")
                .answer("가입 후 발송된 이메일에서 인증 링크를 클릭하여 인증을 완료하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("다른 기기에서 로그인하는 방법은?")
                .answer("각 기기에서 로그인하면 자동으로 계정에 연결됩니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 삭제 후 재가입할 수 있나요?")
                .answer("계정 삭제 후 30일 이내에는 재가입이 불가능합니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("이메일 변경 방법은?")
                .answer("설정 메뉴에서 이메일 주소를 변경할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 정보는 어디서 확인하나요?")
                .answer("계정 정보는 프로필 페이지에서 확인할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정에 연결된 소셜 미디어 계정은?")
                .answer("계정 설정에서 소셜 미디어 계정을 관리할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 인증 방법은?")
                .answer("이메일 인증과 전화 인증이 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("비밀번호 변경은 어떻게 하나요?")
                .answer("설정에서 비밀번호를 변경할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 비활성화는 어떻게 하나요?")
                .answer("설정에서 계정을 비활성화할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("다른 사람의 계정을 어떻게 신고하나요?")
                .answer("고객센터에 문의하여 신고할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 관련 알림은 어디서 설정하나요?")
                .answer("설정 메뉴에서 알림 수신을 설정할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("기타 계정 문의는 어떻게 하나요?")
                .answer("고객센터를 통해 문의하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 보안 문제 발생 시 대처 방법은?")
                .answer("고객센터에 즉시 문의하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("비밀번호를 안전하게 관리하는 방법은?")
                .answer("비밀번호 관리 앱을 사용해 보세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 로그 기록은 어디서 확인하나요?")
                .answer("설정에서 최근 로그인 기록을 확인할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정의 데이터 백업은 어떻게 하나요?")
                .answer("설정에서 데이터 백업 옵션을 사용할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("비밀번호를 잊어버렸을 때 도움을 요청하는 방법은?")
                .answer("비밀번호 찾기 기능을 이용하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("이메일 주소가 변경되었을 때 어떻게 하나요?")
                .answer("기존 이메일로 인증 후 변경할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 관련 문의는 어디로 하나요?")
                .answer("고객센터에 문의하시면 됩니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정의 모든 데이터 삭제 방법은?")
                .answer("설정에서 계정을 삭제하면 모든 데이터가 삭제됩니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 복구 요청 방법은?")
                .answer("고객센터를 통해 복구 요청을 하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("로그인 상태 유지 방법은?")
                .answer("로그인 후 '로그인 상태 유지'를 체크하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정에서 로그아웃하는 방법은?")
                .answer("설정에서 로그아웃 버튼을 클릭하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 사용 내역은 어떻게 확인하나요?")
                .answer("사용 내역은 설정에서 확인할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("다른 사용자와 계정 공유는 가능한가요?")
                .answer("계정 공유는 금지되어 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("2단계 인증 설정은 어떻게 하나요?")
                .answer("설정에서 2단계 인증을 활성화할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("다른 사람의 계정을 어떻게 사용할 수 있나요?")
                .answer("다른 사람의 계정 사용은 금지되어 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("비밀번호 변경 주기는 어떻게 되나요?")
                .answer("보안상 주기적으로 변경하는 것이 좋습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 관련 변경사항은 어디서 확인하나요?")
                .answer("변경 사항은 설정에서 확인할 수 있습니다.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("내 계정 정보는 어떻게 보호하나요?")
                .answer("개인 정보를 안전하게 관리하세요.")
                .admin(admin)
                .build());

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.ACCOUNT)
                .question("계정 해킹 시 대처 방법은?")
                .answer("즉시 비밀번호를 변경하고 고객센터에 신고하세요.")
                .admin(admin)
                .build());
    }
    @Transactional
    protected void insertStudyFaqData(AdminEntity admin) {
        List<FaqEntity> studyFaqs = Arrays.asList(
                new FaqEntity(FaqCategory.STUDY, "스터디 시작은 어떻게 하나요?", "스터디 게시판에서 해당 스터디에 참여신청을 통해 시작할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 참여 방법은 무엇인가요?", "참여 링크를 통해 쉽게 참여할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 모집 마감은 언제인가요?", "스터디 모집 마감일은 게시판에 공지됩니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 진행 중 주의사항은?", "모든 참가자는 규칙을 준수해야 합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 회의록은 어떻게 공유하나요?", "스터디 그룹 내에서 회의록을 공유할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 신청 후 취소할 수 있나요?", "스터디 모집 마감 전까지 취소가 가능합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디의 최대 인원 수는?", "스터디별로 최대 인원 수는 다르며, 게시판에 안내됩니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 소통 방법은?", "스터디 내에서 제공되는 채팅 기능을 사용하세요.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 일정 변경 시 알림은 어떻게 받나요?", "스터디 일정이 변경되면 알림을 받게 됩니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디에 필요한 준비물은?", "각 스터디마다 준비물 목록이 제공됩니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 진행 방식은 어떻게 되나요?", "각 스터디별로 진행 방식이 다를 수 있습니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 시간은 어떻게 정하나요?", "스터디 팀에서 합의하여 정합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 장소는 어떻게 결정하나요?", "스터디 팀에서 결정하며, 공지합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 진행 중 생길 수 있는 문제는?", "팀원과 협의하여 해결해야 합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 목표는 어떻게 설정하나요?", "스터디 시작 시 팀원들과 논의하여 설정합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 중간 점검은 어떻게 하나요?", "정기적으로 팀 회의를 진행하여 점검합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 종료 후 피드백은 어떻게 하나요?", "팀원들과 피드백 세션을 진행합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 종료 후 활동 보고는 어떻게 하나요?", "팀원들과 함께 활동 보고서를 작성합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 진행에 필요한 자료는 어떻게 준비하나요?", "각 팀원이 필요한 자료를 준비해야 합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 중간에 새롭게 들어온 팀원은?", "기존 팀원들이 새 팀원에게 정보를 제공합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 종료 후 자료는 어떻게 보관하나요?", "스터디 자료는 팀에서 공유 드라이브에 보관합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 진행 중 의사소통은 어떻게 하나요?", "온라인 채팅 툴을 이용하여 소통합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 결과 발표는 어떻게 하나요?", "스터디 종료 후 결과를 정리하여 발표합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 계획 변경 시 알림은 어떻게 받나요?", "스터디 플랫폼에서 알림을 받습니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 소모임은 어떻게 운영하나요?", "소모임은 별도로 계획하여 운영합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디에 대한 문의는 어디로 하나요?", "스터디 관련 문의는 고객센터에 해주세요.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디의 규칙은 어떻게 설정하나요?", "스터디 시작 시 팀원들과 합의하여 설정합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 진행 중 발생한 문제 해결 방법은?", "팀원들과 협의하여 해결책을 찾아야 합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 계획은 어떻게 작성하나요?", "팀원들과 협의하여 계획서를 작성합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 목표 달성 시 포상은?", "팀 내에서 포상 방안을 논의합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 일정은 어떻게 관리하나요?", "스터디 일정은 공유 캘린더에 등록하여 관리합니다.", admin),
                new FaqEntity(FaqCategory.STUDY, "스터디 신청 후 언제까지 대기해야 하나요?", "스터디 모집 마감 전까지 대기해야 합니다.", admin)
        );

        faqRepository.saveAll(studyFaqs); // studyFaqs 리스트를 데이터베이스에 저장
    }
    @Transactional
    public void insertProjectFaqData(AdminEntity admin) {
        List<FaqEntity> projectFaqs = Arrays.asList(
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 관리 방법은?", "프로젝트 관리 페이지에서 가능합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "팀원 추가 방법은?", "프로젝트 설정에서 팀원을 초대할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 일정은 어떻게 관리하나요?", "캘린더 기능을 통해 프로젝트 일정을 관리할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 피드백은 어떻게 받나요?", "팀원과 의견을 나누며 피드백을 받을 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 문서는 어디에 저장되나요?", "문서는 프로젝트 페이지 내의 파일 섹션에 저장됩니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 수정 기록은 어떻게 확인하나요?", "프로젝트 기록 탭에서 수정 내역을 확인할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 완료 시 어떻게 처리하나요?", "완료된 프로젝트는 아카이브로 이동됩니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 관리자는 누구인가요?", "프로젝트 관리자는 생성 시 지정한 사용자입니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 관련 알림을 설정할 수 있나요?", "프로젝트 설정에서 알림을 설정할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 예산은 어떻게 관리하나요?", "예산 관리 기능을 통해 할당 및 사용 현황을 관리합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 일정이 변경되면 어떻게 하나요?", "일정 변경 시 모든 팀원에게 알림이 발송됩니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 관련 자료는 어디에서 찾나요?", "프로젝트 문서 페이지에서 찾을 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 계획은 어떻게 작성하나요?", "팀원들과 협의하여 프로젝트 계획서를 작성합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 관련 연락처는 어떻게 관리하나요?", "프로젝트 관리 페이지에서 연락처를 관리할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 목표는 어떻게 설정하나요?", "프로젝트 시작 시 팀원들과 논의하여 설정합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 중간 점검은 어떻게 하나요?", "정기적으로 팀 회의를 진행하여 점검합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 성과는 어떻게 평가하나요?", "프로젝트 종료 후 성과 평가를 진행합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 종료 후 결과 보고서는 어떻게 작성하나요?", "팀원들과 함께 결과 보고서를 작성합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 자료는 어떻게 백업하나요?", "정기적으로 프로젝트 자료를 백업합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트의 중요한 결정은 어떻게 하나요?", "팀원들과 합의하여 결정합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 시작 후 변동 사항은 어떻게 관리하나요?", "변동 사항은 문서화하여 공유합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트에 대한 문의는 어디로 하나요?", "프로젝트 관련 문의는 고객센터에 해주세요.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 참여 시 주의사항은 무엇인가요?", "모든 팀원은 규칙을 준수해야 합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트의 주기적인 업데이트는 어떻게 이루어지나요?", "정기적으로 팀 회의를 통해 업데이트를 진행합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트와 관련된 커뮤니케이션은 어떻게 하나요?", "팀 내에서 제공되는 채팅 기능을 사용합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 관련 자료를 어떻게 공유하나요?", "문서 공유 기능을 이용하여 공유합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 종료 후 데이터 관리는 어떻게 하나요?", "데이터는 아카이브로 이동하여 보관합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 기간은 어떻게 설정하나요?", "프로젝트 관리 페이지에서 기간을 설정합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 변경 사항을 어떻게 공지하나요?", "공지 사항을 통해 모든 팀원에게 전달합니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 종료 후 소감은 어떻게 나누나요?", "팀 회의를 통해 소감을 나눕니다.", admin),
                new FaqEntity(FaqCategory.PROJECT, "프로젝트 진행 중 문제가 발생했을 때 대처 방법은?", "즉시 팀원들과 협의하여 해결합니다.", admin)
        );

        faqRepository.saveAll(projectFaqs); // projectFaqs 리스트를 데이터베이스에 저장
    }
    @Transactional
    public void insertChallengeFaqData(AdminEntity admin) {
        List<FaqEntity> challengeFaqs = Arrays.asList(
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지에 어떻게 참여하나요?", "챌린지 페이지에서 참가 신청 가능합니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 결과는 어떻게 확인하나요?", "챌린지 종료 후 결과 발표 게시판에서 확인할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 진행 중 문의는 어떻게 하나요?", "문의하기 버튼을 통해 문의할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 제출 기한은 언제인가요?", "각 챌린지마다 제출 기한이 다르며, 페이지에 안내됩니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 참여 시 주의사항은?", "참여 규칙을 숙지하고 따라야 합니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 포상은 어떻게 이루어지나요?", "챌린지 종료 후 포상이 지급됩니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 팀 구성은 어떻게 하나요?", "팀원과 협의하여 구성할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 관련 자료는 어디서 찾나요?", "챌린지 페이지의 자료 섹션에서 찾을 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 피드백은 어떻게 제공하나요?", "피드백은 별도의 평가서를 통해 제공됩니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 기간 연장은 가능한가요?", "연장은 챌린지 주최자와 협의해야 합니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 결과 발표는 언제 하나요?", "챌린지 종료 후 일정에 따라 발표됩니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 제출 양식은 어떻게 되나요?", "제출 양식은 챌린지 페이지에서 다운로드할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 참여자 수는 몇 명인가요?", "각 챌린지마다 참여자 수는 다를 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 관련 공지는 어디서 확인하나요?", "공지사항에서 챌린지 관련 정보를 확인할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 참여 후 탈퇴할 수 있나요?", "챌린지 진행 중에는 탈퇴할 수 없습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 결과에 이의 제기는 어떻게 하나요?", "이의 제기는 공식 경로를 통해 제출해야 합니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 시상식은 언제 열리나요?", "챌린지 종료 후 별도의 시상식이 열립니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 참여를 위한 준비물은 무엇인가요?", "준비물은 챌린지 공지에서 확인할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 중 불참 시 처리 방법은?", "불참 시 사전에 공지해야 합니다.", admin),
                new FaqEntity(FaqCategory.CHALLENGE, "챌린지 관련 문의는 어디로 하나요?", "챌린지 관련 문의는 고객센터에 해주세요.", admin)
        );

        faqRepository.saveAll(challengeFaqs); // challengeFaqs 리스트를 데이터베이스에 저장
    }
    @Transactional
    public void insertEtcFaqData(AdminEntity admin) {
        List<FaqEntity> etcFaqs = Arrays.asList(
                new FaqEntity(FaqCategory.ETC, "기타 문의는 어디로?", "기타 문의는 wolfas@wolf.com으로 해주세요.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 문제가 발생했습니다. 어떻게 하나요?", "고객센터에 문의하여 도움을 요청하세요.", admin),
                new FaqEntity(FaqCategory.ETC, "이용약관은 어디서 볼 수 있나요?", "이용약관은 홈페이지 하단의 링크에서 확인할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.ETC, "개인정보 처리 방침은 어떻게 되나요?", "개인정보 처리 방침은 별도의 페이지에서 확인 가능합니다.", admin),
                new FaqEntity(FaqCategory.ETC, "업데이트 알림은 어떻게 받나요?", "설정 메뉴에서 알림 수신을 설정할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.ETC, "제휴 문의는 어떻게 하나요?", "제휴 관련 문의는 partnership@wolf.com으로 해주세요.", admin),
                new FaqEntity(FaqCategory.ETC, "뉴스레터 구독 방법은?", "뉴스레터 구독은 홈페이지에서 신청할 수 있습니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 시간은 어떻게 되나요?", "서비스는 연중무휴 24시간 운영됩니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 종료 시 알림은 어떻게 받나요?", "서비스 종료 시 사전 공지를 통해 알림이 제공됩니다.", admin),
                new FaqEntity(FaqCategory.ETC, "제안사항은 어디에 보내나요?", "제안사항은 feedback@wolf.com으로 보내주시면 됩니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 피드백은 어떻게 주나요?", "고객센터를 통해 피드백을 주시면 됩니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 불만 사항은 어디에 신고하나요?", "불만 사항은 고객센터에 신고해주세요.", admin),
                new FaqEntity(FaqCategory.ETC, "이용자 요구 사항은 어떻게 수집하나요?", "설문조사를 통해 수집합니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 제공 시간은 언제인가요?", "서비스는 24시간 제공됩니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 안전사고 발생 시 대처 방법은?", "고객센터에 신고하여 조치를 요청하세요.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스에 대한 건의 사항은 어디로 보내나요?", "건의 사항은 feedback@wolf.com으로 보내주세요.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 기술적 문제는 어떻게 해결하나요?", "고객센터에 문의하여 지원을 받으세요.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 불만 사항은 어떻게 처리되나요?", "고객센터에서 접수 후 처리합니다.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 계정 문제가 발생하면 어떻게 하나요?", "고객센터에 문의하여 도움을 요청하세요.", admin),
                new FaqEntity(FaqCategory.ETC, "서비스 이용 중 데이터 손실이 발생하면?", "고객센터에 신고하여 복구를 요청하세요.", admin)
        );

        faqRepository.saveAll(etcFaqs); // etcFaqs 리스트를 데이터베이스에 저장
    }
}
