package com.crowdin.client.reports.model;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MatchTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void knownMatchTypesRoundTripThroughEnumAccessorsTest() {
        for (MatchType matchType : MatchType.values()) {
            Match match = new Match();
            match.setMatchType(matchType);

            assertEquals(matchType, match.getMatchType());
            assertEquals(matchType.to(matchType), match.getMatchTypeValue());
            assertEquals("{\"matchType\":\"" + matchType.to(matchType) + "\"}", jsonTransformer.convert(match));

            Match parsed = jsonTransformer.parse(jsonTransformer.convert(match), Match.class);
            assertEquals(matchType, parsed.getMatchType());
            assertEquals(match, parsed);
        }
    }

    @Test
    void knownMatchTypesDeserializeIntoEnumTest() {
        Match match = jsonTransformer.parse("{\"matchType\":\"99-82\",\"price\":1.5}", Match.class);

        assertEquals(MatchType.OPTION_99_82, match.getMatchType());
        assertEquals("99-82", match.getMatchTypeValue());
        assertEquals(Float.valueOf(1.5f), match.getPrice());
    }

    @Test
    void customRangesAreSerializedTest() {
        Match match = new Match();
        match.setMatchTypeValue("100-82");
        match.setPrice(0.5f);

        assertJsonEquals("{\"matchType\":\"100-82\",\"price\":0.5}", jsonTransformer.convert(match));
        assertNull(match.getMatchType());
    }

    @Test
    void customRangesAreDeserializedWithoutDataLossTest() {
        for (String range : new String[]{"100-74", "100-82", "73-50"}) {
            Match match = jsonTransformer.parse("{\"matchType\":\"" + range + "\",\"price\":0.25}", Match.class);

            assertEquals(range, match.getMatchTypeValue());
            assertNull(match.getMatchType(), "no predefined constant for " + range);
            assertJsonEquals("{\"matchType\":\"" + range + "\",\"price\":0.25}", jsonTransformer.convert(match));
        }
    }

    @Test
    void enumSetterReplacesCustomValueAndNullClearsItTest() {
        Match match = new Match();
        match.setMatchTypeValue("100-74");
        match.setMatchType(MatchType.OPTION_100);
        assertEquals("100", match.getMatchTypeValue());

        match.setMatchType(null);
        assertNull(match.getMatchTypeValue());
        assertNull(match.getMatchType());
        assertEquals("{}", jsonTransformer.convert(match));
    }

    @SneakyThrows
    private void assertJsonEquals(String expected, String actual) {
        assertEquals(objectMapper.readTree(expected), objectMapper.readTree(actual), actual);
    }
}
