package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTaskVendorGengoRequest extends AddTaskRequest {

    private TypeVendor type;
    private String vendor;
    private String expertise;
    private String tone;
    private String purpose;
    private String customerMessage;
    private Boolean usePreferred;
    private Boolean editService;
}
