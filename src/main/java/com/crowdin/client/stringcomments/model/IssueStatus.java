package com.crowdin.client.stringcomments.model;

import com.crowdin.client.core.model.EnumConverter;

public enum IssueStatus implements EnumConverter<IssueStatus> {
    RESOLVED, UNRESOLVED;

    public static IssueStatus from(String value) {
        return IssueStatus.valueOf(value.toUpperCase());
    }

    @Override
    public Object to(IssueStatus v) {
        return v.name().toLowerCase();
    }
}