package com.crowdin.client.applications.storage.model;

import com.crowdin.client.core.model.PatchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

/**
 * Patch operation for an application KV record that always serializes {@code value}, even when it is {@code null}.
 * Use it with path {@code /ttl} and a {@code null} value to remove the TTL and make the record permanent
 * (a plain {@link PatchRequest} omits {@code null} values from the request body).
 * <p>
 * Prefer it over a plain {@link PatchRequest} for records that may be {@code secret}: {@code toString()} shows the
 * value only for the {@code /ttl} path and masks it for any other path, while {@link PatchRequest#toString()}
 * prints the value as is.
 * <p>
 * Note: the client omits {@code null}-valued entries inside maps when serializing the request, e.g. {@code {"a": null, "b": 1}} is sent as {@code {"b": 1}}; {@code null} elements inside lists are kept.
 */
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ApplicationKvRecordPatchRequest extends PatchRequest {

    /**
     * Only the value of the {@code /ttl} operation is shown. The value of any other path (including {@code /value},
     * unexpected paths and a {@code null} path) may belong to a {@code secret} record, so it is masked
     *
     * @return string representation without the record value
     */
    @Override
    public String toString() {
        Object value = "/ttl".equals(getPath()) || getValue() == null ? getValue() : "****";
        return "ApplicationKvRecordPatchRequest(op=" + getOp() + ", path=" + getPath() + ", value=" + value + ")";
    }
}
