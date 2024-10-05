package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRegistrationQueryRepository extends JpaRepository<ChallengeRegistrationEntity, Long> {

@Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
        + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "+
        "FROM ChallengeRegistrationEntity r " +
        "JOIN PaymentEntity pay ON pay.registration.registrationId = r.registrationId " +
        "WHERE r.groupPost.groupPostId = :groupId " +
        "AND pay.user.userId = :userId " +
        "AND pay.paymentStatus = 'Y' " +
        "AND r.challengePost.deadline > CURRENT_DATE")
    List<ChallengePreview> findOngoingChallenges(Long groupId, Long userId);


//    @Query("SELECT p FROM ChallengePostEntity p " +
//            "JOIN ChallengeRegistrationEntity r ON r.challengePost.challengePostId = p.challengePostId " +
//            "JOIN PaymentEntity pay ON pay.registrationId = r.registrationId " +
//            "WHERE r.groupPost.groupPostId = :groupId " +
//            "AND pay.userId = :userId " +
//            "AND pay.paymentStatus = 'Y' " +
//            "AND p.challengeDeadline <= CURRENT_DATE")
//    List<ChallengePostEntity> findCompletedChallenges(Long groupId, Long userId);

}
