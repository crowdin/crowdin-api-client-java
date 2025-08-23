package com.crowdin.client.stringcorrections.model;

import lombok.Data;

@Data
public class AddCorrectionRequest {
    private Long stringId;
    private String text;
    private String pluralCategoryName;
}
