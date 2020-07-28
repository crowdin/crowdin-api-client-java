package com.crowdin.client.sourcefiles.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestoreFileRequest extends UpdateOrRestoreFileRequest {

    private Long revisionId;
}
