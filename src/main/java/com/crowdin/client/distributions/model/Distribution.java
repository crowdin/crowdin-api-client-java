package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Distribution {
    private String hash;
    private String exportMode;
    private String name;
    private List<Long> fileIds;
    private String format;
    private String exportPattern;
    private List<Long> labelIds;
    private Date createdAt;
    private Date updatedAt;
}
