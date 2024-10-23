package com.kdt.wolf.domain.challenge.entity;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "challenge_result")
public class ChallengeResultEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_result_id")
  @SequenceGenerator(name = "seq_challenge_result_id", sequenceName = "challenge_result_sequence", allocationSize = 1)
  private Long resultId;


  // GroupPostEntity와 다대일 관계 설정
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_post_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private GroupPostEntity groupPost;

  // ChallengePostEntity와 다대일 관계 설정
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "challenge_post_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ChallengePostEntity challengePost;

  private int totalChallengeAmount;
  private int successfulParticipants;
  private LocalDate resultCreateDate;

  @Builder
  public ChallengeResultEntity(GroupPostEntity groupPost, ChallengePostEntity challengePost, int totalChallengeAmount, int successfulParticipants) {
    this.groupPost = groupPost;
    this.challengePost = challengePost;
    this.totalChallengeAmount = totalChallengeAmount;
    this.successfulParticipants = successfulParticipants;
    this.resultCreateDate = LocalDate.now();
  }
}