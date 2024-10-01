package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_schedule_id")
    @SequenceGenerator(name = "seq_schedule_id", sequenceName = "seq_schedule", allocationSize = 1)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "group_post_id", nullable = false)
    private GroupPostEntity groupPostId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private UserEntity userId;

    @Column(length = 100)
    private String details;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date endTime;

    @Builder
    public ScheduleEntity(GroupPostEntity groupPostId, UserEntity userId, String details, Date startTime, Date endTime) {
        this.groupPostId = groupPostId;
        this.userId = userId;
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
