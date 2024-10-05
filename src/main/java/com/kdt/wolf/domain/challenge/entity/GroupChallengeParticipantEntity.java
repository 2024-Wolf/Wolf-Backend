package com.kdt.wolf.domain.challenge.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "group_challenge_participant")
public class GroupChallengeParticipantEntity extends BaseTimeEntity {
    @EmbeddedId
    private GroupChallengeParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("registrationId") // GroupChallengeParticipantId에 있는 필드와 매핑
    @JoinColumn(name = "registration_id")
    private ChallengeRegistrationEntity challengeRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // GroupChallengeParticipantId에 있는 필드와 매핑
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDate joinDate;

    @Column(columnDefinition = "VARCHAR2(1)")
    private char paymentStatus;

    @Column(columnDefinition = "VARCHAR2(1)")
    private char participationStatus; //인증 여부

    @Builder
    public GroupChallengeParticipantEntity(ChallengeRegistrationEntity challengeRegistration, UserEntity user) {
        this.id = new GroupChallengeParticipantId(challengeRegistration.getRegistrationId(), user.getUserId());
        this.challengeRegistration = challengeRegistration;
        this.user = user;
        this.joinDate = LocalDate.now();
        this.paymentStatus = 'N';  // Default value
        this.participationStatus = 'N';  // Default value
    }

    public void updatePaymentStatus() {
        this.paymentStatus = 'Y';
    }

    public void updateParticipationStatus() {
        this.participationStatus = 'Y';
    }
}
