package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengePostRepository extends JpaRepository<ChallengePostEntity, Long> {

    // 신청 가능
    @Query("select distinct T.challengePostId " +
            "from ChallengePostEntity T left join ChallengeRegistrationEntity R " +
            "on T.challengePostId = R.challengePostId " +
            "where R.registrationId is null or R.groupPostId != :groupPostId")
    List<ChallengePostEntity> findAppliable(@Param("groupPostId")Long groupPostId);

    // 참여 가능
    @Query("select distinct T.challengePostId from ChallengePostEntity T " +
            "where T.challengePostId in " +
            "(" +
            "  select R.challengePostId from ChallengeRegistrationEntity R left join PaymentEntity P" +
            "  on R.registrationId = P.registrationId\n" +
            "  where R.groupPostId = :groupPostId and (P.userId != :userId or P.userId is null)" +
            ")")
    List<ChallengePostEntity> findPayable(@Param("groupPostId")Long groupPostId, @Param("userId")Long userId);

    // 인증 가능
    @Query("select T.challengePostId from ChallengePostEntity T " +
            "where T.challengePostId in " +
            "(" +
            "  select R.challengePostId from ChallengeRegistrationEntity R join PaymentEntity P" +
            "  on R.registrationId = P.registrationId" +
            "  where R.challengePostId not in" +
            "  (" +
            "    select V.challengePostId from VerificationEntity V where V.userId = ? and V.verificationStatus = 'Y'" +
            "  )" +
            "  and R.groupPostId = :groupPostId" +
            ")")
    List<ChallengePostEntity> findCertifiable(@Param("groupPostId")Long groupPostId);

    // 인증 완료
    @Query("select T.challengePostId from ChallengePostEntity T join ChallengeRegistrationEntity R " +
            "on T.challengePostId = R.challengePostId " +
            "where R.challengePostId in " +
            "(" +
            "  select V.challengePostId from VerificationEntity V left join ChallengeResultEntity S " +
            "  on V.challengePostId = S.challengePostId" +
            "  where V.userId = :userId and V.verificationStatus='Y' and S.challengePostId is null" +
            ")" +
            "and R.groupPostId = :groupPostId")
    List<ChallengePostEntity> findCertificationCompleted(@Param("userId")Long userId, @Param("groupPostId")Long groupPostId);

    // 챌린지 종료
    @Query("select T.challengePostId from ChallengePostEntity T join ChallengeRegistrationEntity R " +
            "on T.challengePostId = R.challengePostId " +
            "where R.challengePostId in " +
            "(" +
            "  select S.challengePostId from ChallengeResultEntity S" +
            "  where R.challengePostId = S.challengePostId" +
            ")" +
            "and R.groupPostId = :groupPostId")
    List<ChallengePostEntity> findChallengeFinished(@Param("groupPostId")Long groupPostId);
}
