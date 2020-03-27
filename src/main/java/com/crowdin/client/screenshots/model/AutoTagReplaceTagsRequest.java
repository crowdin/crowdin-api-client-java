package com.crowdin.client.screenshots.model;

import lombok.Data;

@Data
public class AutoTagReplaceTagsRequest extends ReplaceTagsRequest {

    private boolean autoTag;
}
