package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateLanguageServiceTaskStringsBasedRequest extends AddTaskStringsBasedRequest {

    private Type type;
    private String vendor;
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    private Boolean includeUntranslatedStringsOnly;
}
