package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "meeting")
public class MeetingEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_seq_id")
    @SequenceGenerator(name = "meeting_seq_id", sequenceName = "meeting_seq", allocationSize = 1)
    @Column(name = "meeting_id")
    private Long meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @Column(name = "meeting_date", nullable = false)
    private LocalDate meetingDate;

    @Column(name = "participants")
    private String participants;

    @Column(name = "start_time", nullable = false)
    private LocalDate startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

    @Builder
    public MeetingEntity(GroupPostEntity groupPost, LocalDate meetingDate, String participants, LocalDate startTime, LocalDate endTime) {
        this.groupPost = groupPost;
        this.meetingDate = meetingDate;
        this.participants = participants;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void updateMeetingDetails(String participants, LocalDate startTime, LocalDate endTime) {
        this.participants = participants;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

