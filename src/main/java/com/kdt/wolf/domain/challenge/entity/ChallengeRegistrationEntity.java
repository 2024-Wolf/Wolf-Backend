package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challengeRegistration")
public class ChallengeRegistrationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_registration_id")
  @SequenceGenerator(name = "seq_challenge_registration_id", sequenceName = "challenge_registration_sequence", allocationSize = 1)
  private Long registrationId;

  // 챌린지 id
  private Long challengePostId;

  // 신청 그룹 id
  private Long groupPostId;

  private Long ChallengeAmount;
  private Date registrationDate;

  @Builder
  public void ChallengeRegistrationEntity(Long challengePostId, Long groupPostId, Long challengeAmount) {
    this.challengePostId = challengePostId;
    this.groupPostId = groupPostId;
    this.ChallengeAmount = challengeAmount;
    this.registrationDate = new Date();
  }

}