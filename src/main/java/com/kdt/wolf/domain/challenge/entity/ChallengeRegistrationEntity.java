package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "challenge_registration")
public class ChallengeRegistrationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_reg_id")
  @SequenceGenerator(name = "seq_challenge_reg_id", sequenceName = "challenge_reg_seq", allocationSize = 1)
  private Long registrationId;

  // 챌린지 id
  private Long challengePostId;

  // 신청 그룹 id
  private Long groupPostId;

  private Long ChallengeAmount;
  private LocalDate registrationDate;

  @Builder
  public ChallengeRegistrationEntity(Long challengePostId, Long groupPostId, Long challengeAmount) {
    this.challengePostId = challengePostId;
    this.groupPostId = groupPostId;
    this.ChallengeAmount = challengeAmount;
    this.registrationDate = LocalDate.now();
  }

}