package com.crowdin.client.tasks.model.pending;

import com.crowdin.client.tasks.model.AssigneeRequest;
import com.crowdin.client.tasks.model.TypeVendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePendingVendorTaskRequest extends AddPendingTaskRequest {
    private TypeVendor type;
    private String vendor;
    private Boolean skipAssignedStrings;
    private List<AssigneeRequest> assignees;
}
