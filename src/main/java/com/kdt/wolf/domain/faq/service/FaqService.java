package com.kdt.wolf.domain.faq.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.faq.dao.FaqDao;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqCreateRequest;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqDetail;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqItems;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqUpdateRequest;
import com.kdt.wolf.domain.faq.dto.response.FaqPageResponse;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.global.dto.PageResponse;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqDao faqDao;
    private final AdminDao AdminDao;

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
                faq.getCategory(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getAdmin().getNickname(),
                faq.getCreatedTime().toString(),
                faq.getModifiedTime().toString()
        );
    }

    @Transactional
    public Long createFaq(FaqCreateRequest request) {
        AdminEntity admin = AdminDao.findById(request.adminId());
        FaqEntity faqEntity = FaqEntity.builder()
                .category(findCategory(request.category()))
                .question(request.question())
                .answer(request.answer())
                .admin(admin)
                .build();
        return faqDao.save(faqEntity);
    }

    private FaqCategory findCategory(String category) {
        for (FaqCategory faqCategory : FaqCategory.values()) {
            if (faqCategory.getName().equals(category)) {
                return faqCategory;
            }
        }
        throw new NotFoundException(ExceptionCode.NOT_FOUND_FAQ_CATEGORY);
    }


    @Transactional
    public FaqDetail updateFaq(Long faqId, FaqUpdateRequest request) {
        FaqEntity faq = faqDao.findById(faqId);
        faq.update(findCategory(request.category()), request.question(), request.answer());

        return new FaqDetail(
                faq.getCategory(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getAdmin().getNickname(),
                faq.getCreatedTime().toString(),
                faq.getModifiedTime().toString()
        );
    }

    @Transactional
    public Long deleteFaq(Long faqId) {
        return faqDao.deleteById(faqId);
    }
}
