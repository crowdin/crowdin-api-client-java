package com.crowdin.client.tasks.model.pending;

import com.crowdin.client.tasks.model.AssigneeRequest;
import com.crowdin.client.tasks.model.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePendingTaskRequest extends AddPendingTaskRequest {
    private Type type;
    private List<AssigneeRequest> assignees;
}
