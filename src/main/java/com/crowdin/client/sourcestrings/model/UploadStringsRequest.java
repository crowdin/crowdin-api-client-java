package com.crowdin.client.sourcestrings.model;

import lombok.Data;

import java.util.List;

@Data
public class UploadStringsRequest {

    private Long branchId;
    private Long storageId;
    private String type;
    private Integer parserVersion;
    private List<Long> labelIds;
    private Boolean updateStrings;
    private Boolean cleanupMode;
    private ImportOptions importOptions;
}
