package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.List;

@Data
public class UpdateFileRequest extends UpdateOrRestoreFileRequest {

    private Long storageId;
    private UpdateOption updateOption;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private List<Long> attachLabelIds;
    private List<Long> detachLabelIds;
}
