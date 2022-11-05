package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateFileRequest extends UpdateOrRestoreFileRequest {

    private Long storageId;
    private UpdateOption updateOption;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private List<Long> attachLabelIds;
    private List<Long> detachLabelIds;
}
