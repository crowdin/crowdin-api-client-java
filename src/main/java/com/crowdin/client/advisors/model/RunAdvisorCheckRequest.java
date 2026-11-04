package com.crowdin.client.advisors.model;

import lombok.Data;

import java.util.List;

/**
 * At most one of {@link #category} or {@link #inspectors} may be set; if neither is set, every inspector is re-checked.
 */
@Data
public class RunAdvisorCheckRequest {

    private String category;
    private List<RunAdvisorCheckInspector> inspectors;
}
