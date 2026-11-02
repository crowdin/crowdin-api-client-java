package com.crowdin.client.projectsgroups.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class HtmlFileFormatSettings extends FileFormatSettings {
    private Boolean contentSegmentation;
    /**
     * Legacy boolean representation of {@code excludedElements}. It is filled when the API returns a boolean and is
     * sent only when {@link #excludedElementList} is not set.
     *
     * @deprecated the API documents {@code excludedElements} as an array of CSS selectors, use
     * {@link #excludedElementList} instead
     */
    @Deprecated
    @JsonIgnore
    private Boolean excludedElements;
    /**
     * CSS selectors of elements that should not be translated (JSON key {@code excludedElements})
     */
    @JsonIgnore
    private List<String> excludedElementList;
    private Long srxStorageId;
    private List<String> inlineTags;
    private Boolean hideAttributeValues;
    private Boolean customSegmentation;

    /**
     * Writes {@code excludedElements}: the documented array if set, otherwise the legacy boolean
     */
    @JsonGetter("excludedElements")
    private Object writeExcludedElements() {
        return this.excludedElementList != null ? this.excludedElementList : this.excludedElements;
    }

    /**
     * Reads {@code excludedElements} returned either as an array of CSS selectors or as a legacy boolean.
     * Other (undocumented) representations are ignored instead of failing the whole response.
     */
    @JsonSetter("excludedElements")
    private void readExcludedElements(JsonNode value) {
        this.excludedElements = null;
        this.excludedElementList = null;
        if (value == null || value.isNull()) {
            return;
        }
        if (value.isBoolean()) {
            this.excludedElements = value.booleanValue();
        } else if (value.isArray()) {
            List<String> selectors = new ArrayList<>();
            for (JsonNode selector : value) {
                selectors.add(selector.isNull() ? null : selector.asText());
            }
            this.excludedElementList = selectors;
        }
    }
}
