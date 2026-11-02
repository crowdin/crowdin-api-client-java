package com.crowdin.client.applications.storage.model;

import lombok.Data;
import lombok.ToString;

@Data
public class AddApplicationKvRecordRequest {

    private String key;
    /**
     * Any JSON value except {@code null}: a string, number, boolean, array (List) or object (Map).
     * Masked in {@code toString()} when the record is {@code secret}.
     * <p>
     * Note: the client omits {@code null}-valued entries inside maps when serializing the request, e.g. {@code {"a": null, "b": 1}} is sent as {@code {"b": 1}}; {@code null} elements inside lists are kept
     */
    @ToString.Exclude
    private Object value;
    private Boolean secret;
    /**
     * Time to live in seconds (60-31536000). Omit for a permanent record
     */
    private Integer ttl;

    /**
     * {@code toString()} view of {@link #value}: masked when the record is {@code secret}
     */
    @ToString.Include(name = "value")
    private Object valueForToString() {
        return Boolean.TRUE.equals(this.secret) && this.value != null ? "****" : this.value;
    }
}
