package com.kdt.wolf.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Table(name = "activity_metrics")
public class ActivityMetricsEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId  // UserEntity의 ID를 ActivityMetrics의 ID로 사용
    private UserEntity user;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int totalStudyParticipation = 0;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int memberExperienceCount = 0;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int leaderExperienceCount = 0;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int challengeSuccessCount = 0;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int activityRatingGood = 0;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int activityRatingSoso = 0;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int activityRatingBad = 0;
}
