package com.crowdin.client.reports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostEstimateFuzzyModeGenerateReportRequestTest {

    @Test
    void from() {
        Mode response = Mode.from("perfect");
        assertEquals(Mode.PERFECT, response);

        Mode response1 = Mode.from("100");
        assertEquals(Mode.OPTION_100, response1);

        Mode response2 = Mode.from("99-95");
        assertEquals(Mode.OPTION_99_95, response2);

        Mode response3 = Mode.from("94-90");
        assertEquals(Mode.OPTION_94_90, response3);

        Mode response4 = Mode.from("89-80");
        assertEquals(Mode.OPTION_89_80, response4);

        Mode response5 = Mode.from("no_match");
        assertEquals(Mode.NO_MATCH, response5);

        Mode response6 = Mode.from("do_not_match");
        assertNull(response6);

    }

    @Test
    void to() {
        Object response = (Mode.PERFECT).to(Mode.PERFECT);
        assertEquals(response, "perfect");

        Object response1 = (Mode.NO_MATCH).to(Mode.NO_MATCH);
        assertEquals(response1, "no_match");

        Object response2 = (Mode.OPTION_100).to(Mode.OPTION_100);
        assertEquals(response2, "100");

        Object response3 = (Mode.OPTION_89_80).to(Mode.OPTION_89_80);
        assertEquals(response3, "89-80");

        Object response4 = (Mode.OPTION_94_90).to(Mode.OPTION_94_90);
        assertEquals(response4, "94-90");

        Object response5 = (Mode.OPTION_99_95).to(Mode.OPTION_99_95);
        assertEquals(response5, "99-95");
    }

}