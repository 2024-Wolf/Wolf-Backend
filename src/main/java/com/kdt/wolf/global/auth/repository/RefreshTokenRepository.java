package com.kdt.wolf.global.auth.repository;

import com.kdt.wolf.global.auth.entity.RefreshTokenEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    @Query("select r from RefreshTokenEntity r where r.user.userId = :userId")
    Optional<RefreshTokenEntity> findByUserId(long userId);

    @Query("select r from RefreshTokenEntity r where r.refreshTokenValue = :refreshToken")
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

    @Query("select r from RefreshTokenEntity r where r.admin.adminId = :adminId")
    Optional<RefreshTokenEntity> findByAdminId(long adminId);
}
