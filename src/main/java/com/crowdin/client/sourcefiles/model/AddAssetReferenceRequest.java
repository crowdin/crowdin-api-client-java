package com.crowdin.client.sourcefiles.model;

import lombok.Data;

@Data
public class AddAssetReferenceRequest {
    private Long storageId;
    private String name;
}
