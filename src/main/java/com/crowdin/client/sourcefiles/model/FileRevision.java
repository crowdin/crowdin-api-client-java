package com.crowdin.client.sourcefiles.model;

import lombok.Data;

import java.util.Date;

@Data
public class FileRevision {

    private Integer id;
    private Integer projectId;
    private Integer fileId;
    private Integer restoreToRevision;
    private FileRevisionInfo info;
    private Date date;
}
