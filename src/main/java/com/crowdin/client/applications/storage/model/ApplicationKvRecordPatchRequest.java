package com.crowdin.client.applications.storage.model;

import com.crowdin.client.core.model.PatchRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

/**
 * Patch operation for an application KV record that always serializes {@code value}, even when it is {@code null}.
 * Use it with path {@code /ttl} and a {@code null} value to remove the TTL and make the record permanent
 * (a plain {@link PatchRequest} omits {@code null} values from the request body).
 */
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ApplicationKvRecordPatchRequest extends PatchRequest {

    /**
     * The value of a {@code /value} operation may belong to a {@code secret} record, so it is masked;
     * other values (e.g. {@code /ttl}) are shown
     *
     * @return string representation without the record value
     */
    @Override
    public String toString() {
        Object value = "/value".equals(getPath()) && getValue() != null ? "****" : getValue();
        return "ApplicationKvRecordPatchRequest(op=" + getOp() + ", path=" + getPath() + ", value=" + value + ")";
    }
}
