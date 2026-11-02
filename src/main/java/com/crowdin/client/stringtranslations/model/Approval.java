package com.crowdin.client.stringtranslations.model;

import lombok.Data;

import java.util.Date;

@Data
public class Approval {

    private Long id;
    private User user;
    private Long translationId;
    private Long stringId;
    /**
     * Asset approvals only
     */
    private Long fileId;
    /**
     * Enterprise only
     */
    private Long workflowStepId;
    /**
     * Enterprise only, correction approvals
     */
    private Long correctionId;
    private String languageId;
    private Date createdAt;
}
