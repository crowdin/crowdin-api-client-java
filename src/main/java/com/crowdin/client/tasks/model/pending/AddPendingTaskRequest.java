package com.crowdin.client.tasks.model.pending;

import com.crowdin.client.tasks.model.AddTaskRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AddPendingTaskRequest extends AddTaskRequest {
    private Long precedingTaskId;
    private Date deadline;
}