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
     * Legacy integer view of {@link #minValue}. A fractional value within the {@link Integer} range is truncated
     * towards zero (e.g. {@code 1.5} &rarr; {@code 1}, {@code -1.5} &rarr; {@code -1}). A value outside the
     * {@link Integer} range (or not a number) is returned as {@code null} instead of being clamped.
     *
     * @return minimal value truncated to an integer, or {@code null} if it is not set or does not fit into an integer
     * @deprecated the API defines {@code min} as a number; use {@code getMinValue()} ({@link #minValue}) instead,
     * which returns the exact value
     */
    @Deprecated
    public Integer getMin() {
        return toInteger(this.minValue);
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
     * Legacy integer view of {@link #maxValue}. A fractional value within the {@link Integer} range is truncated
     * towards zero (e.g. {@code 9.9} &rarr; {@code 9}, {@code -9.9} &rarr; {@code -9}). A value outside the
     * {@link Integer} range (or not a number) is returned as {@code null} instead of being clamped.
     *
     * @return maximal value truncated to an integer, or {@code null} if it is not set or does not fit into an integer
     * @deprecated the API defines {@code max} as a number; use {@code getMaxValue()} ({@link #maxValue}) instead,
     * which returns the exact value
     */
    @Deprecated
    public Integer getMax() {
        return toInteger(this.maxValue);
    }

    /**
     * @param max maximal value
     * @deprecated use {@link #maxValue} instead
     */
    @Deprecated
    public void setMax(Integer max) {
        this.maxValue = max == null ? null : max.doubleValue();
    }

    private static Integer toInteger(Double value) {
        if (value == null || value.isNaN() || value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
            return null;
        }
        return value.intValue();
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
