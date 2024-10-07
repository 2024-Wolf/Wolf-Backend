package com.kdt.wolf.global.fcm.repository;

import com.kdt.wolf.global.fcm.entity.FcmEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmRepository extends JpaRepository<FcmEntity, Long> {
    @Query("SELECT f.fcmToken FROM FcmEntity f WHERE f.user.userId = :targetUserId")
    List<String> findTokensByUserId(Long targetUserId);

    @Query("SELECT f FROM FcmEntity f WHERE f.fcmToken = :fcmToken")
    Optional<FcmEntity> findByFcmToken(String fcmToken);
}
