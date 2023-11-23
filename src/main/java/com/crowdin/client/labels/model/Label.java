package com.crowdin.client.labels.model;

import com.crowdin.client.core.model.BooleanInt;
import lombok.Data;

@Data
public class Label {

    private Long id;
    private String title;
    private BooleanInt isSystem;
}
