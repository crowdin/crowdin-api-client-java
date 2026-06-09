package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorStringsBasedRequest extends AddTaskStringsBasedRequest {

    private TypeVendor type;
    private String vendor;
    private Boolean skipAssignedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Date deadline;
}
