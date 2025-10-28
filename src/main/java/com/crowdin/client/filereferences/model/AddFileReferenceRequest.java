package com.crowdin.client.filereferences.model;

import lombok.Data;

/**
 * Request body for creating a new File Reference.
 */
@Data
public class AddFileReferenceRequest {
    private String name;     // Name of the reference
    private String type;     // Type of the reference (e.g., "file" or "asset")
    private Long fileId;     // ID of the file this reference points to
}
