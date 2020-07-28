package com.crowdin.client.screenshots.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddTagRequest extends ReplaceTagsRequest {

    private Long stringId;
    private Position position;
}
