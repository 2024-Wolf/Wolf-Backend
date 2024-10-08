package com.kdt.wolf.domain.faq.entity;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "faq")
public class FaqEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_faq_id")
    @SequenceGenerator(name = "seq_faq_id", sequenceName = "faq_sequence", allocationSize = 1)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

    public FaqEntity(String question, String answer, AdminEntity admin) {
        this.question = question;
        this.answer = answer;
        this.admin = admin;
    }
}
