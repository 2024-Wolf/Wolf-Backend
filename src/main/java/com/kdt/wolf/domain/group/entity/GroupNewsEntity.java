package com.kdt.wolf.domain.group.entity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_news")
public class GroupNewsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_news_seq_gen")
    @SequenceGenerator(name = "group_news_seq_gen", sequenceName = "group_news_seq", allocationSize = 1)
    private Long groupNewsId;

    @Column(nullable = false)
    private String newsContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "group_post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GroupPostEntity groupPost; // GroupPost와 연관관계

    public GroupNewsEntity(String newsContent, GroupPostEntity groupPost) {
        this.newsContent = newsContent;
        this.groupPost = groupPost;
    }

    public void updateNewsContent(String newContent) {
        this.newsContent = newContent;
    }
}