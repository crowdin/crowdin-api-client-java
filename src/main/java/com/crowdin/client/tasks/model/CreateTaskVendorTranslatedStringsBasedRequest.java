package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorTranslatedStringsBasedRequest extends AddTaskStringsBasedRequest {

    private TypeVendor type;
    private String vendor;
    private String expertise;
    private String subject;
}
