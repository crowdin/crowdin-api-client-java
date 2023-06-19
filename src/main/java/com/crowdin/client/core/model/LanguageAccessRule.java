package com.crowdin.client.core.model;

import lombok.Data;

@Data
public class LanguageAccessRule {
    private boolean allContent;
    private Object workflowStepIds;
}
