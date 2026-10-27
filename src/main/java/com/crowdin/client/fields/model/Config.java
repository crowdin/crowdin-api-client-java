package com.crowdin.client.fields.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Config {
    private List<Option> options;
    private List<Location> locations;

    /**
     * @deprecated the API defines {@code min} as a number, use {@link #minValue} instead
     */
    @Deprecated
    @JsonIgnore
    private Integer min;

    /**
     * @deprecated the API defines {@code max} as a number, use {@link #maxValue} instead
     */
    @Deprecated
    @JsonIgnore
    private Integer max;

    /**
     * Minimal value of a number field (JSON key {@code min})
     */
    @JsonProperty("min")
    private Double minValue;

    /**
     * Maximal value of a number field (JSON key {@code max})
     */
    @JsonProperty("max")
    private Double maxValue;

    private String units;

    /**
     * @return minimal value truncated to an integer
     * @deprecated use {@link #minValue} instead
     */
    @Deprecated
    public Integer getMin() {
        return this.minValue == null ? null : this.minValue.intValue();
    }

    /**
     * @param min minimal value
     * @deprecated use {@link #minValue} instead
     */
    @Deprecated
    public void setMin(Integer min) {
        this.minValue = min == null ? null : min.doubleValue();
    }

    /**
     * @return maximal value truncated to an integer
     * @deprecated use {@link #maxValue} instead
     */
    @Deprecated
    public Integer getMax() {
        return this.maxValue == null ? null : this.maxValue.intValue();
    }

    /**
     * @param max maximal value
     * @deprecated use {@link #maxValue} instead
     */
    @Deprecated
    public void setMax(Integer max) {
        this.maxValue = max == null ? null : max.doubleValue();
    }

    @Data
    public static class Option {
        private String label;
        private String value;
    }

    @Data
    public static class Location {
        private String place;
    }
}
