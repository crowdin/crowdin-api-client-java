package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateLanguageServiceTaskRequest extends AddTaskFilesBasedRequest {

    private TypeVendor type;
    private String vendor;
    @Deprecated
    private Boolean skipUntranslatedStrings;
    private Boolean includePreTranslatedStringsOnly;
    @Deprecated
    private Boolean includeUntranslatedStringsOnly;
}
