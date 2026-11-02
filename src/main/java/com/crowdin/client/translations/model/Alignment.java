package com.crowdin.client.translations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Alignment {
    private String sourceWord;
    private String sourceLemma;
    private String targetWord;
    private String targetLemma;
    private Integer match;
    /**
     * @deprecated the API returns a fractional number; use {@link #probabilityValue} instead.
     */
    @Deprecated
    @JsonIgnore
    private Integer probability;
    @JsonProperty("probability")
    private Double probabilityValue;

    /**
     * @deprecated the API returns a fractional number; use {@link #probabilityValue} instead.
     * @return probability truncated to an integer
     */
    @Deprecated
    public Integer getProbability() {
        return this.probabilityValue == null ? null : this.probabilityValue.intValue();
    }

    /**
     * @deprecated use {@link #probabilityValue} instead.
     * @param probability probability
     */
    @Deprecated
    public void setProbability(Integer probability) {
        this.probability = probability;
        this.probabilityValue = probability == null ? null : probability.doubleValue();
    }
}
