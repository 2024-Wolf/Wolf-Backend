package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import com.kdt.wolf.domain.challenge.entity.GroupChallengeParticipantEntity;
import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupChallengeParticipantRepository  extends JpaRepository<GroupChallengeParticipantEntity, Long> {

    @Query("Select cp "
            + "FROM GroupChallengeParticipantEntity cp "
            + "WHERE cp.challengeRegistration = :registration "
            + "AND cp.user = :user")
    GroupChallengeParticipantEntity findGroupChallengeParticipantEntity(ChallengeRegistrationEntity registration, UserEntity user);

    @Query("Select cp "
            + "FROM GroupChallengeParticipantEntity cp "
            + "WHERE cp.user = :user "
            + "AND cp.challengeRegistration.groupPost = :groupPost "
            + "AND cp.challengeRegistration.challengePost = :challenge")
    List<GroupChallengeParticipantEntity> findMemberByGroupPost(GroupMemberEntity user, ChallengePostEntity challenge, GroupPostEntity groupPost);

}
