package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorOhtRequest extends AddTaskRequest {

    private Type type;
    private String vendor;
    private String expertise;
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Boolean includeUntranslatedStringsOnly;
}
