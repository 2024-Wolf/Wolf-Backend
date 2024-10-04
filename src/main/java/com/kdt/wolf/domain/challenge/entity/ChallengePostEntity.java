package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "challenge_post")
public class ChallengePostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_post_id")
    @SequenceGenerator(name = "seq_challenge_post_id", sequenceName = "challenge_post_sequence", allocationSize = 1)
    private Long challengePostId;

    // 작성자 id
    private Long userId;

    private String img;
    private String title;
    private String content;
    private String manner;
    private String awardContent;
    private LocalDate date;
    private LocalDate deadline;

    @Builder
    public ChallengePostEntity(Long userId, String img, String title, String content, String manner, LocalDate deadline) {
        this.userId = userId;
        this.img = img;
        this.title = title;
        this.content = content;
        this.manner = manner;
        this.awardContent = null;
        this.date = LocalDate.now();
        this.deadline = deadline;
    }

}