package com.kdt.wolf.global.dto;

import org.springframework.data.domain.Page;

public record PageResponse(
        Integer totalPages,
        Integer currentPage,
        Long totalElements,
        boolean isPageLast,
        Integer size,
        Integer numberOfElements,
        boolean isPageFirst
) {
    public PageResponse(Page<?> page) {
        this(
                page.getTotalPages(),
                page.getNumber() + 1,  // 페이지 번호 0부터 시작하므로 +1
                page.getTotalElements(),
                page.isLast(),
                page.getSize(),
                page.getNumberOfElements(),
                page.isFirst()
        );
    }
}
