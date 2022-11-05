package com.crowdin.client.screenshots.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddTagRequest extends ReplaceTagsRequest {

    private Long stringId;
    private Position position;
}
