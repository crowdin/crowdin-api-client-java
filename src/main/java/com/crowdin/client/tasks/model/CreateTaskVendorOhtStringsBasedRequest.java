package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorOhtStringsBasedRequest extends AddTaskStringsBasedRequest {

    private Type type;
    private String vendor;
    private String expertise;
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Boolean includeUntranslatedStringsOnly;
}
