package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challengepost")
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
    private Date date;
    private Date deadline;

    @Builder
    public void ChallengePostEntity(Long userId, String img, String title, String content, String manner, Date deadline) {
        this.userId = userId;
        this.img = img;
        this.title = title;
        this.content = content;
        this.manner = manner;
        this.awardContent = null;
        this.date = new Date();
        this.deadline = deadline;
    }

}