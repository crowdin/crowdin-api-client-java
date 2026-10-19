package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.Date;

@Data
public class FileRevision {

    private Long id;
    private Long projectId;
    private Long fileId;
    private Long restoreToRevision;
    /**
     * @deprecated not part of the File Revision resource in the current API documentation; always {@code null}
     */
    @Deprecated
    private FileRevisionInfo info;
    private Date date;
}
