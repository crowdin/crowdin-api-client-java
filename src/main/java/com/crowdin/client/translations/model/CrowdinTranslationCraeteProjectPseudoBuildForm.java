package com.crowdin.client.translations.model;

import lombok.Data;

@Deprecated
@Data
public class CrowdinTranslationCraeteProjectPseudoBuildForm implements BuildProjectTranslationRequest {

    private Long branchId;
    private Boolean pseudo;
    private String prefix;
    private String suffix;
    private Integer lengthTransformation;
    private CharTransformation charTransformation;
}
