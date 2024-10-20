package com.kdt.wolf.domain.user.repository;

import com.kdt.wolf.domain.user.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = {"activityMetrics"})
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNickname(String nickname);

    @Override
    @EntityGraph(attributePaths = {"activityMetrics"})
    @NonNull
    List<UserEntity> findAll();

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END "
            + "FROM UserEntity u "
            + "WHERE u.nickname = :nickname AND u.status = 'ACTIVE'")
    boolean existsByNickname(@Param("nickname") String nickname);
}
