package com.crowdin.client.stringtranslations.model;

import lombok.Data;

@Data
public class AddApprovalRequest {

    private Long translationId;
    /**
     * Enterprise only. Approves a correction instead of a translation; cannot be combined with {@code translationId}
     */
    private Long correctionId;
}
