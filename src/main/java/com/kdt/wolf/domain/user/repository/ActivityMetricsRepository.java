package com.kdt.wolf.domain.user.repository;

import com.kdt.wolf.domain.user.entity.ActivityMetricsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMetricsRepository extends JpaRepository<ActivityMetricsEntity, Long> {

    @Query("select a from ActivityMetricsEntity a where a.user.userId = :userId")
    Optional<ActivityMetricsEntity> findByUserId(Long userId);
}
