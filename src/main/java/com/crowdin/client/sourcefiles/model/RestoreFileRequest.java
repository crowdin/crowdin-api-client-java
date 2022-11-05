package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RestoreFileRequest extends UpdateOrRestoreFileRequest {

    private Long revisionId;
}
