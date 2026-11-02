package com.crowdin.client.labels.model;

import com.crowdin.client.core.model.BooleanInt;
import lombok.Data;

@Data
public class Label {

    private Long id;
    private String title;
    /**
     * String-based projects only. The API returns a JSON boolean, which is mapped to {@link BooleanInt}
     */
    private BooleanInt isSystem;
    /**
     * Enterprise only
     */
    private Boolean isShared;
}
