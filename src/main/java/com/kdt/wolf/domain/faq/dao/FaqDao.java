package com.kdt.wolf.domain.faq.dao;


import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import com.kdt.wolf.domain.faq.repository.FaqRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FaqDao {
    private final FaqRepository faqRepository;

    public long save(FaqEntity faqEntity) {
        return faqRepository.save(faqEntity).getId();
    }

    public Page<FaqEntity> findAll(Pageable pageable) {
        Page<FaqEntity> faqs = faqRepository.findAll(pageable);
        if(faqs.isEmpty()) {
            throw new NotFoundException();
        }
        return faqs;
    }

    public FaqEntity findById(Long faqId) {
        return faqRepository.findById(faqId).orElseThrow(NotFoundException::new);
    }

    public Long deleteById(Long faqId) {
        faqRepository.deleteById(faqId);
        return faqId;
    }

    public Page<FaqEntity> findByCategory(FaqCategory category, Pageable pageable) {
        return faqRepository.findByCategory(category, pageable);
    }
}
