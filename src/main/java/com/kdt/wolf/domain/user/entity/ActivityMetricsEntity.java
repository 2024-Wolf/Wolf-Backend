package com.kdt.wolf.domain.user.entity;

import com.kdt.wolf.domain.user.dto.UserDto.ActivityMetric;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId  // UserEntity의 ID를 ActivityMetrics의 ID로 사용
    private UserEntity user;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int totalStudyParticipation;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int memberExperienceCount;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int leaderExperienceCount;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int challengeSuccessCount;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int activityRatingGood;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int activityRatingSoso;

    @Column(columnDefinition = "NUMBER DEFAULT 0")
    private int activityRatingBad;

    public ActivityMetricsEntity(UserEntity user) {
        this.user = user;
        this.totalStudyParticipation = 0;
        this.memberExperienceCount = 0;
        this.leaderExperienceCount = 0;
        this.challengeSuccessCount = 0;
        this.activityRatingGood = 0;
        this.activityRatingSoso = 0;
        this.activityRatingBad = 0;
    }

    public ActivityMetric toResponse() {
        return new ActivityMetric(
                this.totalStudyParticipation,
                this.memberExperienceCount,
                this.leaderExperienceCount,
                this.challengeSuccessCount,
                this.activityRatingGood,
                this.activityRatingSoso,
                this.activityRatingBad
        );
    }
}
