package com.crowdin.client.tasks.model;

import com.crowdin.client.core.model.EnumConverter;

/**
 * Enterprise only. Defines the scope used by {@code skipAssignedStrings}: skip strings already assigned in any
 * task ({@link #ALL}) or only in tasks of the same workflow step ({@link #SAME_WORKFLOW_STEP}).
 */
public enum SkipAssignedStringsScope implements EnumConverter<SkipAssignedStringsScope> {
    ALL("all"), SAME_WORKFLOW_STEP("sameWorkflowStep");

    private final String value;

    SkipAssignedStringsScope(String value) {
        this.value = value;
    }

    public static SkipAssignedStringsScope from(String value) {
        for (SkipAssignedStringsScope scope : SkipAssignedStringsScope.values()) {
            if (scope.value.equals(value)) {
                return scope;
            }
        }
        return null;
    }

    @Override
    public String to(SkipAssignedStringsScope v) {
        return v.value;
    }
}
