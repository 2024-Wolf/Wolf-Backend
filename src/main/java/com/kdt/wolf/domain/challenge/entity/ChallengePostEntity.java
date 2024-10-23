package com.kdt.wolf.domain.challenge.entity;

import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest.ChallengeCreateRequest;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "challenge_post")
public class ChallengePostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_challenge_post_id")
    @SequenceGenerator(name = "seq_challenge_post_id", sequenceName = "challenge_post_sequence", allocationSize = 1)
    private Long challengePostId;

    // 작성자 id (어드민)
    private Long userId;

    private String img;
    private String title;
    private String content;
    private String manner;
    private String awardContent;
    private LocalDate deadline;

    @Builder
    public ChallengePostEntity(Long userId, String img, String title, String content, String manner, String awardContent, LocalDate deadline) {
        this.userId = userId;
        this.img = img;
        this.title = title;
        this.content = content;
        this.manner = manner;
        this.awardContent = awardContent;
        this.deadline = deadline;
    }

    public void updateChallengePost(ChallengeCreateRequest request, String imageUrl) {
        this.img = imageUrl;
        this.title = request.title();
        this.content = request.content();
        this.manner = request.manner();
        this.awardContent = request.awardContent();
        this.deadline = request.deadline();
    }

}