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
     *
     * @throws IllegalArgumentException for any other representation (e.g. a single string, a number, an object, or
     * an array containing non-string elements), so that unexpected data is reported instead of silently dropped
     */
    @JsonSetter("excludedElements")
    private void readExcludedElements(JsonNode value) {
        if (value == null || value.isNull()) {
            this.excludedElements = null;
            this.excludedElementList = null;
            return;
        }
        if (value.isBoolean()) {
            this.excludedElements = value.booleanValue();
            this.excludedElementList = null;
            return;
        }
        if (!value.isArray()) {
            throw new IllegalArgumentException("excludedElements must be an array of CSS selectors or a boolean, got: " + value);
        }
        List<String> selectors = new ArrayList<>();
        for (JsonNode selector : value) {
            if (selector.isNull()) {
                selectors.add(null);
            } else if (selector.isTextual()) {
                selectors.add(selector.textValue());
            } else {
                throw new IllegalArgumentException("excludedElements must contain only strings, got: " + value);
            }
        }
        this.excludedElements = null;
        this.excludedElementList = selectors;
    }
}
