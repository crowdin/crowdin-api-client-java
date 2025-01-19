package com.crowdin.client.projectsgroups.model;

import lombok.Data;

@Data
public class TmPreTranslate {

    private Boolean enabled;
    private String autoApproveOption;
    private String minimumMatchRatio;
}
