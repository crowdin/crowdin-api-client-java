package com.crowdin.client.stringcomments.model;

import lombok.Data;

import java.util.List;

@Data
public class AddStringCommentRequest {

    private String text;
    private Long stringId;
    /**
     * File-based projects only, used instead of {@link #stringId} to comment on a file
     */
    private Long fileId;
    private String targetLanguageId;
    private Type type;
    private String issueType;
    /**
     * @deprecated not part of the "Add String Comment" request schema, ignored by the API
     */
    @Deprecated
    private IssueStatus issueStatus;
    private List<Attachment> attachments;
    /**
     * Crowdin Enterprise only
     */
    private Boolean isShared;
}