package com.kdt.wolf.domain.challenge.entity;


import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class GroupChallengeParticipantId implements Serializable {

    private Long registrationId;
    private Long userId;

    public GroupChallengeParticipantId(Long registrationId, Long userId) {
        this.registrationId = registrationId;
        this.userId = userId;
    }
}
