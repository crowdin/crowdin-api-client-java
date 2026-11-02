package com.crowdin.client.applications.storage.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
public class ApplicationKvRecord {

    private String key;
    /**
     * Any JSON value except {@code null}: a string, number, boolean, array (List) or object (Map).
     * Masked in {@code toString()} when the record is {@code secret}
     */
    @ToString.Exclude
    private Object value;
    private Boolean secret;
    private Date createdAt;
    private Date updatedAt;
    private Date expiresAt;

    /**
     * {@code toString()} view of {@link #value}: masked when the record is {@code secret}
     */
    @ToString.Include(name = "value")
    private Object valueForToString() {
        return Boolean.TRUE.equals(this.secret) && this.value != null ? "****" : this.value;
    }
}
