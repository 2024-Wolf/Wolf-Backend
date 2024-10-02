package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengePostRepository extends JpaRepository<ChallengePostEntity, Long> {
}