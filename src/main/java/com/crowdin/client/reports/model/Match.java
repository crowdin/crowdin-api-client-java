package com.crowdin.client.reports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Match {

    /**
     * Match type as sent to and returned by the API (JSON key {@code matchType}): {@code perfect}, {@code 100}
     * or any percentage range, e.g. {@code 99-82}, {@code 100-74}.
     */
    @JsonProperty("matchType")
    private String matchTypeValue;
    private Float price;

    /**
     * Predefined view of {@link #matchTypeValue}.
     *
     * @return the matching {@link MatchType} constant, or {@code null} if the value is not set or is a range that has
     * no predefined constant (e.g. {@code 100-74}); the original value is always available in {@link #matchTypeValue}
     */
    public MatchType getMatchType() {
        return this.matchTypeValue == null ? null : MatchType.from(this.matchTypeValue);
    }

    /**
     * Sets {@link #matchTypeValue} from a predefined constant. Use {@code setMatchTypeValue(String)} for ranges that
     * have no constant (e.g. {@code 100-82}).
     *
     * @param matchType predefined match type
     */
    public void setMatchType(MatchType matchType) {
        this.matchTypeValue = matchType == null ? null : (String) matchType.to(matchType);
    }
}
