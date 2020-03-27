package com.crowdin.client.screenshots.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tag {

    private Long id;
    private Long screenshotId;
    private Long stringId;
    private Position position;
    private Date createdAt;
}
