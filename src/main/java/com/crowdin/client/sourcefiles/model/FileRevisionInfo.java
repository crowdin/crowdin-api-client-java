package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class FileRevisionInfo {

    private FileRevisionInfoAttribute added;
    private FileRevisionInfoAttribute deleted;
    private FileRevisionInfoAttribute updated;
}
