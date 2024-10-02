package com.kdt.wolf.domain.user.repository;

import com.kdt.wolf.domain.user.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

}
