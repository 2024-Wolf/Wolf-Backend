package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionCommentResponse {
    private final Long commentId;
    private final Long parentsId;
    private final CommentAuthor authorId;
    private final String commentDetails;
    private final String commentImageUrl;
    private final LocalDateTime createTime;

    public QuestionCommentResponse(QuestionCommentEntity comment) {
        this.commentId = comment.getCommentId();
        this.parentsId = comment.getParentComment() != null ? comment.getParentComment().getCommentId() : null;
        this.authorId = new CommentAuthor(comment.getAuthor());
        this.commentDetails = comment.getCommentDetails();
        this.commentImageUrl = comment.getCommentImageUrl();
        this.createTime = comment.getCreateTime();
    }

    @Getter
    public static class CommentAuthor {
        private final Long userId;
        private final String userNickname;
        private final String userProfileImg;

        public CommentAuthor(UserEntity user) {
            this.userId = user.getUserId();
            this.userNickname = user.getNickname();
            this.userProfileImg = user.getProfilePicture();
        }
    }
}

