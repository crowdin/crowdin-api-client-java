package com.crowdin.client.tasks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CrowdinVendorOhtTaskCreateFormRequest extends AddTaskRequest {

    private String title;
    private String languageId;
    private List<Long> fileIds;
    private Integer type;
    private String vendor;
    private Status status;
    private String description;
    private String expertise;
    private Boolean includePreTranslatedStringsOnly;
    private List<Long> labelIds;
    private Date dateFrom;
    private Date dateTo;
}
