package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.group.entity.TaskEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;

public class TaskResponse {
    private final Author author;
    private final String details;
    private final String status;

    public TaskResponse(TaskEntity taskEntity) {
        this.author = new Author(taskEntity.getUser());
        this.details = taskEntity.getDetails();
        this.status = taskEntity.getStatus().toString().toLowerCase();
    }

    public class Author {
        private final Long userId;
        private final String userNickname;
        private final String userProfileImg;

        public Author(UserEntity user) {
            this.userId = user.getUserId();
            this.userNickname = user.getNickname();
            this.userProfileImg = user.getProfilePicture();
        }
    }
}
