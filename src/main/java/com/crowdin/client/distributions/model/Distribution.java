package com.crowdin.client.distributions.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Distribution {
    private String hash;
    private String manifestUrl;
    private String name;
    private List<Integer> bundleIds;
    private Date createdAt;
    private Date updatedAt;
    private String exportMode;
    private List<Long> fileIds;
}
