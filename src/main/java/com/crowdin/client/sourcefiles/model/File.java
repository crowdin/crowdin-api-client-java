package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.Priority;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class File extends FileInfo {

    private Long revisionId;
    private Priority priority;
    private ImportOptions importOptions;
    private ExportOptions exportOptions;
    private List<String> excludedTargetLanguages;
    private Date createdAt;
    private Date updatedAt;
}
