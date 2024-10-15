package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.group.entity.common.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskRequest {
    @NotNull
    private Long authorId;

    private String details;
    private String status;
}
