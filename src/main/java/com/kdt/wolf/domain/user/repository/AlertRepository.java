package com.kdt.wolf.domain.user.repository;

import com.kdt.wolf.domain.user.entity.AlertEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlertRepository extends JpaRepository<AlertEntity, Long> {
    @Query("SELECT a FROM AlertEntity a WHERE a.user.userId = :userId")
    List<AlertEntity> findByUserId(Long userId);

    @Query("SELECT a FROM AlertEntity a WHERE a.user.userId = :userId AND a.isRead = false")
    List<AlertEntity> findUnReadAlarmsByUserId(Long userId);
}
