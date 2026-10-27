package com.crowdin.client.bundles.model;

import lombok.Data;

import java.util.Date;

@Data
public class BundleExport {

    private String identifier;
    private String status;
    private int progress;
    private BundleAttributes attributes;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
    /**
     * @deprecated deprecated in the API, may be removed in the future
     */
    @Deprecated
    private String eta;

}