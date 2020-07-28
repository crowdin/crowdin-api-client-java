package com.crowdin.client.screenshots.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AutoTagReplaceTagsRequest extends ReplaceTagsRequest {

    private Boolean autoTag;
}
