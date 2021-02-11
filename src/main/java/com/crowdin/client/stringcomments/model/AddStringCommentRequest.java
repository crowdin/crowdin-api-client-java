package com.crowdin.client.stringcomments.model;

import lombok.Data;

@Data
public class AddStringCommentRequest {

    private String text;
    private Long stringId;
    private String targetLanguageId;
    private Type type;
    private String issueType;
    private IssueStatus issueStatus;
}