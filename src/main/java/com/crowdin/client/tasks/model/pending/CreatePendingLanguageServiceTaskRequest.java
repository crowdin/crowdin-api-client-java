package com.crowdin.client.tasks.model.pending;

import com.crowdin.client.tasks.model.TypeVendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreatePendingLanguageServiceTaskRequest extends AddPendingTaskRequest {
    private TypeVendor type;
    private String vendor;
}
