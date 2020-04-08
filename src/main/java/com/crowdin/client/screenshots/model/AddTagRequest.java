package com.crowdin.client.screenshots.model;

import lombok.Data;

@Data
public class AddTagRequest extends ReplaceTagsRequest {

    private Long stringId;
    private Position position;
}
