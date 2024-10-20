package com.kdt.wolf.domain.user.repository;

import com.kdt.wolf.domain.user.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
