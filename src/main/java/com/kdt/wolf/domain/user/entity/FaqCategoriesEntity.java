package com.kdt.wolf.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "faqCategories")
public class FaqCategoriesEntity {
// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_faqcategory_id")
// @SequenceGenerator(name = "seq_faqcategory_id", sequenceName = "faqcategory_sequence", allocationSize = 1)

    @Id
    private Long categoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private LocalDate createDate;

@Builder
    public FaqCategoriesEntity(long categoryId, String categoryName) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.createDate = LocalDate.now();
    }
}
