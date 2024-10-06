package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import com.kdt.wolf.domain.challenge.entity.GroupChallengeParticipantEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupChallengeParticipantRepository  extends JpaRepository<GroupChallengeParticipantEntity, Long> {

    @Query("Select P "
            + "FROM GroupChallengeParticipantEntity P "
            + "WHERE P.challengeRegistration = :registration "
            + "AND P.user = :user")
    GroupChallengeParticipantEntity findGroupChallengeParticipantEntity(ChallengeRegistrationEntity registration, UserEntity user);
}
