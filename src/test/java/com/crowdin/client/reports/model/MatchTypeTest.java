package com.crowdin.client.reports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTypeTest {

    @Test
    void from() {
        MatchType response = MatchType.from("perfect");
        assertEquals(MatchType.PERFECT, response);

        MatchType response1 = MatchType.from("100");
        assertEquals(MatchType.OPTION_100, response1);

        MatchType response2 = MatchType.from("99-82");
        assertEquals(MatchType.OPTION_99_82, response2);

        MatchType response3 = MatchType.from("81-60");
        assertEquals(MatchType.OPTION_81_60, response3);

        MatchType response4 = MatchType.from("do_not_match");
        assertNull(response4);

    }

    @Test
    void to() {
        Object response = (MatchType.PERFECT).to(MatchType.PERFECT);
        assertEquals(response, "perfect");

        Object response1 = (MatchType.OPTION_100).to(MatchType.OPTION_100);
        assertEquals(response1, "100");

        Object response2 = (MatchType.OPTION_99_82).to(MatchType.OPTION_99_82);
        assertEquals(response2, "99-82");

        Object response3 = (MatchType.OPTION_81_60).to(MatchType.OPTION_81_60);
        assertEquals(response3, "81-60");
    }
}