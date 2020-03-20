package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class RestoreFileRequest extends UpdateOrRestoreFileRequest {

    private Long revisionId;
}
