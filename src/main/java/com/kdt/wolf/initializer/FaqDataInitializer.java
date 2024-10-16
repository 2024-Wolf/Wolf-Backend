package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.domain.faq.repository.FaqRepository;
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
        // Account 카테고리
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

        //

        System.out.println("Admin 및 FAQ 더미 데이터가 성공적으로 삽입되었습니다.");
    }
}
