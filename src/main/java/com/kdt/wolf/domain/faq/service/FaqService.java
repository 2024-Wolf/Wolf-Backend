package com.kdt.wolf.domain.faq.service;

import com.kdt.wolf.domain.faq.dao.FaqDao;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqItems;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqResponse;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
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
}
