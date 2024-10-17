package com.kdt.wolf.domain.faq.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.faq.dao.FaqDao;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqCreateRequest;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqDetail;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqItems;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqUpdateRequest;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqAdminResponse;
import com.kdt.wolf.domain.faq.dto.response.FaqAdminPageResponse;
import com.kdt.wolf.domain.faq.dto.response.FaqPageResponse;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqDao faqDao;
    private final AdminDao AdminDao;

    public FaqAdminPageResponse getFaqs(Pageable pageable) {
        Page<FaqEntity> faqs = faqDao.findAll(pageable);

        if (faqs.isEmpty()){
            return new FaqAdminPageResponse(List.of(), new PageResponse(Page.empty()));
        }

        return new FaqAdminPageResponse(
                faqs.getContent().stream()
                        .map(faqEntity -> new FaqAdminResponse(faqEntity.getId(), faqEntity.getCategory().toString().toLowerCase(), faqEntity.getQuestion(), faqEntity.getAdmin().getNickname(), faqEntity.createdTime.toLocalDate()))
                        .toList(),
                new PageResponse(faqs)
        );
    }

    public FaqPageResponse getFaqsByCategory(FaqCategory category, Pageable pageable) {
        Page<FaqEntity> faqsByCategory = findFaqsByCategory(category, pageable);

        if(faqsByCategory.isEmpty()) {
            return new FaqPageResponse(new ArrayList<>(), new PageResponse(Page.empty()));
        }

        return new FaqPageResponse(
                faqsByCategory.getContent().stream()
                        .map(faqEntity -> new FaqItems(faqEntity.getQuestion(), faqEntity.getAnswer()))
                        .toList(),
                new PageResponse(faqsByCategory)
        );

    }

    private Page<FaqEntity> findFaqsByCategory(FaqCategory category, Pageable pageable) {
        return faqDao.findByCategory(category, pageable);
    }

    public FaqDetail getFaqDetail (Long faqId) {
        FaqEntity faq = faqDao.findById(faqId);
        return new FaqDetail(
                faqId,
                faq.getCategory(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getAdmin().getNickname(),
                faq.getCreatedTime().toLocalDate(),
                faq.getModifiedTime().toLocalDate()
        );
    }

    @Transactional
    public Long createFaq(Long authorId, FaqCreateRequest request) {
        AdminEntity admin = AdminDao.findById(authorId);
        System.out.println("관리자 찾기 성공");
        FaqEntity faqEntity = FaqEntity.builder()
                .category(findCategory(request.category()))
                .question(request.question())
                .answer(request.answer())
                .admin(admin)
                .build();
        System.out.println("엔티티 빌더 성공");
        return faqDao.save(faqEntity);
    }

    private FaqCategory findCategory(String category) {
        for (FaqCategory faqCategory : FaqCategory.values()) {
            System.out.println(faqCategory);
            if (faqCategory.getName().equals(category)) {
                System.out.println("findCatefory 성공 : " + faqCategory);
                return faqCategory;
            }
        }
        throw new NotFoundException(ExceptionCode.NOT_FOUND_FAQ_CATEGORY);
    }


    @Transactional
    public FaqDetail updateFaq(Long faqId, FaqUpdateRequest request) {
        FaqEntity faq = faqDao.findById(faqId);
        System.out.println("findById 성공" );
        faq.update(findCategory(request.category()), request.question(), request.answer());
        System.out.println("update 성공");
        return new FaqDetail(
                faqId,
                faq.getCategory(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getAdmin().getNickname(),
                faq.getCreatedTime().toLocalDate(),
                faq.getModifiedTime().toLocalDate()
        );
    }

    @Transactional
    public Long deleteFaq(Long faqId) {
        return faqDao.deleteById(faqId);
    }
}
