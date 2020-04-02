package com.crowdin.client.translationstatus.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FileProgress extends Progress {

    private Long fileId;
}
