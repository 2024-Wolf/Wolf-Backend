package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "challenge_result")
public class ChallengeResultEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_result_id")
  @SequenceGenerator(name = "seq_challenge_result_id", sequenceName = "challenge_result_sequence", allocationSize = 1)
  private Long resultId;

  // 그룹 id
  private Long groupPostId;

  // 챌린지 id
  private Long challengePostId;

  private int totalChallengeAmount;
  private int successfulParticipants;
  private LocalDate resultCreateDate;

  @Builder
  public ChallengeResultEntity(Long groupPostId, Long challengePostId, int totalChallengeAmount, int successfulParticipants) {
    this.groupPostId = groupPostId;
    this.challengePostId = challengePostId;
    this.totalChallengeAmount = totalChallengeAmount;
    this.successfulParticipants = successfulParticipants;
    this.resultCreateDate = LocalDate.now();
  }
}