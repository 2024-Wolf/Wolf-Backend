package com.kdt.wolf.domain.group.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_news")
public class GroupNewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String newsContent; // 한줄소식 내용

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost; // GroupPost와 연관관계

    public GroupNewsEntity(String newsContent, GroupPostEntity groupPost) {
        this.newsContent = newsContent;
        this.groupPost = groupPost;
    }

    public void updateNewsContent(String newContent) {
        this.newsContent = newContent;
    }
}