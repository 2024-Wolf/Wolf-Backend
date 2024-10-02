package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;
import oracle.sql.DATE;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "verificationEntity")
public class VerificationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_verification_id")
  @SequenceGenerator(name = "seq_verification_id", sequenceName = "verification_sequence", allocationSize = 1)
  private Long verificationId;

  // 신청 id
  private Long registrationId;

  // 신청 회원 id
  private Long userId;

  private String certificationNo;
  private String institutionName;
  private String verificationContent;
  private char verificationStatus;
  private LocalDate verificationDate;

  @Builder
  public void VerificationEntity(Long registrationId, Long userId, String certificationNo, String institutionName, String verificationContent) {
    this.registrationId = registrationId;
    this.userId = userId;
    this.certificationNo = certificationNo;
    this.institutionName = institutionName;
    this.verificationContent = verificationContent;
    this.verificationStatus = 'N';
    this.verificationDate = LocalDate.now();
  }

  public void updateVerification() {
    this.verificationStatus = 'Y';
  }
}