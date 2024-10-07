package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskEnterpriseVendorStringsBasedRequest extends AddEnterpriseTaskStringsBasedRequest {

    private Long workflowStepId;
    private Boolean skipAssignedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
    private Date startedAt;

}
