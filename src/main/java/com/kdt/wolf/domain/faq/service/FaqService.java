package com.kdt.wolf.domain.faq.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.faq.dao.FaqDao;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqCreateRequest;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqDetail;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqItems;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqResponse;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqUpdateRequest;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqDao faqDao;
    private final AdminDao AdminDao;

    public FaqResponse getFaqs() {
        List<FaqEntity> faqs = faqDao.findAll();
        Map<String, List<FaqItems>> response = new HashMap<>();

        for (FaqEntity faq : faqs) {
            String category = faq.getCategory().getName();
            FaqItems faqItem = new FaqItems(faq.getQuestion(), faq.getAnswer());

            // 카테고리별로 faqItems 추가
            response.computeIfAbsent(category, k -> new ArrayList<>()).add(faqItem);
        }

        return new FaqResponse(response);
    }

    public FaqDetail getFaqDetail (Long faqId) {
        FaqEntity faq = faqDao.findById(faqId);
        return new FaqDetail(
                faq.getCategory(),
                faq.getQuestion(),
                faq.getAnswer(),
                faq.getAdmin().getAdminNickname(),
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
                faq.getAdmin().getAdminNickname(),
                faq.getCreatedTime().toString(),
                faq.getModifiedTime().toString()
        );
    }

    @Transactional
    public Long deleteFaq(Long faqId) {
        return faqDao.deleteById(faqId);
    }
}
