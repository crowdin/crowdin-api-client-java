package com.crowdin.client.applications.installations.model;

import lombok.Data;

/**
 * Where the app is loaded from: hosted by Crowdin ({@link BundleMode#INTERNAL})
 * or served from an external URL ({@link BundleMode#EXTERNAL}, {@code url} is required).
 */
@Data
public class ApplicationBundle {

    private BundleMode mode;
    private String url;
}
