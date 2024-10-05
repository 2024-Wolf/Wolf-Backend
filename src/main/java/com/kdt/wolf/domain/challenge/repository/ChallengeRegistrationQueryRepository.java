package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRegistrationQueryRepository extends JpaRepository<ChallengeRegistrationEntity, Long> {
    //TODO : Preview가 아니라,,, 다른 데이터 클래스를 만들어야할 듯. 프리뷰 내용 + 인증 여부 아니면 쿼리에서 나눠도 되는데 .. 비효율적임
    //TODO : 결제 여부도 넣으면 쿼리 개수를 줄일 수 있지 않을까 ?
    /** 진행 중인 챌린지: 신청 O, 결제 O, 마감일 이전 */
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "+
            "FROM ChallengeRegistrationEntity r " +
            "JOIN PaymentEntity pay ON pay.registration.registrationId = r.registrationId " +
            "WHERE r.groupPost.groupPostId = :groupId " +
            "AND pay.user.userId = :userId " +
            "AND pay.paymentStatus = 'Y' " +
            "AND r.challengePost.deadline > CURRENT_DATE")
    List<ChallengePreview> findOngoingChallenges(Long groupId, Long userId);

    /**  완료된 챌린지: 신청 O, 결제 O, 마감 O */
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "+
            "FROM ChallengeRegistrationEntity r " +
            "JOIN PaymentEntity pay ON pay.registration.registrationId = r.registrationId " +
            "WHERE r.groupPost.groupPostId = :groupId " +
            "AND pay.user.userId = :userId AND pay.paymentStatus = 'Y' " +
            "AND r.challengePost.deadline <= CURRENT_DATE")
    List<ChallengePreview> findCompletedChallenges(Long groupId, Long userId);

    // 신청 가능
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.challengePost.createdTime, r.challengePost.deadline) "+
            "FROM ChallengeRegistrationEntity r " +
            "WHERE r.groupPost.groupPostId != :groupId ")
    List<ChallengePreview> findApplicableChallenges(Long groupId);

    // 참여 가능
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "+
            "FROM ChallengeRegistrationEntity r " +
            "JOIN PaymentEntity pay ON pay.registration.registrationId = r.registrationId " +
            "WHERE r.groupPost.groupPostId = :groupId " +
            "AND pay.user.userId = :userId AND pay.paymentStatus = 'N'")
    List<ChallengePreview> findJoinableChallenges(Long groupId, Long userId);

}
