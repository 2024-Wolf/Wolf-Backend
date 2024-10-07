package com.kdt.wolf.domain.challenge.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class GroupChallengeParticipantId implements Serializable {

    @Column(name = "registration_id")
    private Long registrationId;

    @Column(name = "user_id")
    private Long userId;

    public GroupChallengeParticipantId(Long registrationId, Long userId) {
        this.registrationId = registrationId;
        this.userId = userId;
    }
}
