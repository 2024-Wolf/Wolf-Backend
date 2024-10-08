package com.kdt.wolf.domain.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_admin_id")
    @SequenceGenerator(name = "seq_admin_id", sequenceName = "admin_sequence", allocationSize = 1)
    private Long adminId;

    private String adminEmail;
    private String adminPassword;
    private String adminNickname;
    private String adminName;

    @Builder
    public AdminEntity(String adminEmail, String adminPassword, String adminNickname, String adminName) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminNickname = adminNickname;
        this.adminName = adminName;
    }
}
