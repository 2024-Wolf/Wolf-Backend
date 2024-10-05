package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRegistrationQueryRepository extends JpaRepository<ChallengeRegistrationEntity, Long> {

    /** 진행 중인 챌린지: 신청 O, 결제 O, 마감일 이전  + 인증 여부 */
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND r.challengePost.deadline >= CURRENT_DATE "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'Y'"
            + "AND cp.participationStatus = 'Y'") // 인증 완료
    List<ChallengePreview> findCertifiedChallenges(Long groupId, Long userId);


    // 인증 가능
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND r.challengePost.deadline >= CURRENT_DATE "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'Y' "  // 결제 완료
            + "AND cp.participationStatus = 'N'") // 인증 미완료
    List<ChallengePreview> findCertifiableChallenges(Long groupId, Long userId);


    /**  완료된 챌린지: 신청 O, 결제 O, 마감 O */
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND r.challengePost.deadline < CURRENT_DATE "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'Y' ")  // 결제 완료
    List<ChallengePreview> findCompletedChallenges(Long groupId, Long userId);

    // 신청 가능
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "p.challengePostId, p.img, p.title, p.createdTime, p.deadline) "
            + "FROM ChallengePostEntity p "
            + "WHERE NOT EXISTS ("
                + "SELECT 1 "
                + "FROM ChallengeRegistrationEntity r "
                + "WHERE r.groupPost.groupPostId = :groupId "
                + "AND r.challengePost.challengePostId = p.challengePostId"
            + ")")
    List<ChallengePreview> findApplicableChallenges(Long groupId);

    // 참여 가능
    @Query("SELECT new com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview ("
            + "r.challengePost.challengePostId, r.challengePost.img, r.challengePost.title, r.registrationDate, r.challengePost.deadline) "
            + "FROM ChallengeRegistrationEntity r "
            + "JOIN FETCH GroupChallengeParticipantEntity cp ON cp.challengeRegistration.registrationId = r.registrationId "
            + "WHERE r.groupPost.groupPostId = :groupId "
            + "AND cp.user.userId = :userId "
            + "AND cp.paymentStatus = 'N'") // 결제 미완료
    List<ChallengePreview> findJoinableChallenges(Long groupId, Long userId);

}
