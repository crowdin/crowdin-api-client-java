package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class Approval {

    private Long id;
    private User user;
    private Long translationId;
    private Long stringId;
    private String languageId;
    private Long workflowStepId;
    private Date createdAt;
}
