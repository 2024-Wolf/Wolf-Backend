package com.kdt.wolf.domain.faq.dao;


import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.domain.faq.repository.FaqRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FaqDao {
    private final FaqRepository faqRepository;

    public long save(FaqEntity faqEntity) {
        return faqRepository.save(faqEntity).getId();
    }

    public List<FaqEntity> findAll() {
        List<FaqEntity> faqs = faqRepository.findAll();
        if(faqs.isEmpty()) {
            throw new NotFoundException();
        }
        return faqs;
    }
}
