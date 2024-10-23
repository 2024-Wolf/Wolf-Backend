package com.kdt.wolf.domain.challenge.entity;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "challenge_registration")
public class ChallengeRegistrationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_reg_id")
  @SequenceGenerator(name = "seq_challenge_reg_id", sequenceName = "challenge_reg_seq", allocationSize = 1)
  private Long registrationId;

  // ChallengePostEntity와 다대일 관계 설정
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "challenge_post_id")
  private ChallengePostEntity challengePost;

  // GroupPostEntity와 다대일 관계 설정
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_post_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private GroupPostEntity groupPost;

  private Long challengeAmount;
  private LocalDate registrationDate;

  @Builder
  public ChallengeRegistrationEntity(ChallengePostEntity challengePost, GroupPostEntity groupPost, Long challengeAmount) {
    this.challengePost = challengePost;
    this.groupPost = groupPost;
    this.challengeAmount = challengeAmount;
    this.registrationDate = LocalDate.now();
  }

}