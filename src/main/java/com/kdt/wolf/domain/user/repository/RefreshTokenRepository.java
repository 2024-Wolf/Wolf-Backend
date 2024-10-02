package com.kdt.wolf.domain.user.repository;

import com.kdt.wolf.domain.user.entity.RefreshTokenEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    @Query("select r from RefreshTokenEntity r where r.user.userId = :userId")
    Optional<RefreshTokenEntity> findByUserId(long userId);
}
