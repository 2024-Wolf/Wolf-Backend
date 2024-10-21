package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.TaskEntity;
import lombok.Getter;

@Getter
public class TaskResponse {
    private final Long id;
    private final String details;
    private final String status;

    public TaskResponse(TaskEntity taskEntity) {
        this.id = taskEntity.getTaskId();
        this.details = taskEntity.getDetails();

        switch(taskEntity.getStatus().toString()){
            case "NOT_STARTED":
                this.status = "기획 중";
                break;
            case "IN_PROGRESS":
                this.status = "진행 중";
                break;
            default:
                this.status = "완료";
                break;
        }
    }

}
