package com.crowdin.client.sourcefiles.model;

import lombok.Data;

/**
 * @deprecated not part of the File Revision resource in the current API documentation
 */
@Deprecated
@Data
public class FileRevisionInfo {

    private FileRevisionInfoAttribute added;
    private FileRevisionInfoAttribute deleted;
    private FileRevisionInfoAttribute updated;
}
