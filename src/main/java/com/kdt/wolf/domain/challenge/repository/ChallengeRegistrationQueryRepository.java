package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import java.util.List;

import com.kdt.wolf.domain.challenge.entity.GroupChallengeParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRegistrationQueryRepository extends JpaRepository<ChallengeRegistrationEntity, Long> {

    /** 진행 중인 챌린지: 신청 O, 결제 O, 마감일 이전  + 인증 여부 */
    // 인증 가능
    @Query("SELECT r "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND r.challengePost.deadline >= CURRENT_DATE "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'Y' "  // 결제 완료
            + "AND cp.participationStatus = 'N'") // 인증 미완료
    List<ChallengeRegistrationEntity> findCertifiableChallenges(Long groupId, Long userId);


    @Query("SELECT r "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND r.challengePost.deadline >= CURRENT_DATE "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'Y'"
            + "AND cp.participationStatus = 'Y'") // 인증 완료
    List<ChallengeRegistrationEntity> findCertifiedChallenges(Long groupId, Long userId);

    /**  완료된 챌린지: 신청 O, 결제 O, 마감 O */
    @Query("SELECT r "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND r.challengePost.deadline < CURRENT_DATE "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'Y' ")  // 결제 완료
    List<ChallengeRegistrationEntity> findCompletedChallenges(Long groupId, Long userId);


    // 참여 가능
    @Query("SELECT r "
            + "FROM ChallengeRegistrationEntity r "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND NOT EXISTS("
                + "SELECT 1 "
                + "FROM GroupChallengeParticipantEntity cp "
                + "WHERE cp.user.userId = :userId"
            + ")")
    List<ChallengeRegistrationEntity> findJoinableChallenges(Long groupId, Long userId);

    // 신청 가능
    @Query("SELECT p "
            + "FROM ChallengePostEntity p "
            + "WHERE NOT EXISTS ("
                + "SELECT 1 "
                + "FROM ChallengeRegistrationEntity r "
                + "WHERE r.groupPost.groupPostId = :groupId "
                + "AND r.challengePost.challengePostId = p.challengePostId"
            + ")")
    List<ChallengePostEntity> findApplicableChallenges(Long groupId);

    // 결제 가능
    @Query(
            "SELECT cp.challengeRegistration "
            + "FROM GroupChallengeParticipantEntity cp "
            + "WHERE cp.challengeRegistration.groupPost.groupPostId = :groupId "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'N'"
            )
    List<ChallengeRegistrationEntity> findPayableChallenge(Long groupId, Long userId);

    // 챌린지 신청 정보 조회
    @Query("SELECT r "
            + "FROM ChallengeRegistrationEntity r "
            + "WHERE r.groupPost.groupPostId = :groupPostId "
            + "AND r.challengePost.challengePostId = :challengePostId")
    ChallengeRegistrationEntity findChallengeRegistration(Long groupPostId, Long challengePostId);

}
