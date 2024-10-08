package com.kdt.wolf.domain.faq.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqItems;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.domain.faq.repository.FaqRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class FaqServiceTest {
    @Autowired
    private FaqService faqService;

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private AdminRepository adminRepository;


    @BeforeEach
    void setUp() {
        AdminEntity adminEntity = adminRepository.save(
                AdminEntity.builder()
                    .adminEmail("adminEmail")
                    .adminPassword("adminPassword")
                    .adminName("adminName")
                    .adminNickname("adminNickname")
                    .build()
        );

        faqRepository.save(
                FaqEntity.builder()
                        .category(FaqCategory.STUDY)
                        .question("question")
                        .answer("answer")
                        .admin(adminEntity)
                        .build()
        );

        faqRepository.save(
                FaqEntity.builder()
                        .category(FaqCategory.STUDY)
                        .question("question2")
                        .answer("answer2")
                        .admin(adminEntity)
                        .build()
        );

        faqRepository.save(FaqEntity.builder()
                .category(FaqCategory.STUDY)
                .question("question3")
                .answer("answer3")
                .admin(adminEntity)
                .build());
    }

    @Test
    @DisplayName("모든 faq를 조회한다.")
    void getAllFaq() {
        //when
        Map<String, List<FaqItems>> response = faqService.getFaqs().faqItems();
        //then
        assertEquals(3, response.get("스터디").size());
    }
}