package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFileRequest extends UpdateOrRestoreFileRequest {

    private Long storageId;
    private String name;
    private UpdateOption updateOption;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private List<Long> attachLabelIds;
    private List<Long> detachLabelIds;
    private Boolean replaceModifiedContext;
}
