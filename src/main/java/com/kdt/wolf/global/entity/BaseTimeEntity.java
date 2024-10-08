package com.kdt.wolf.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @Column(name = "created_time", nullable = false, updatable = false)
    public LocalDateTime createdTime;

    @Column(name = "modified_time", nullable = false)
    public LocalDateTime modifiedTime;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdTime = now;
        this.modifiedTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedTime = LocalDateTime.now();
    }
}
