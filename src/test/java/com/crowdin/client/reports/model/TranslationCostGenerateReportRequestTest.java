package com.crowdin.client.reports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslationCostGenerateReportRequestTest {

    @Test
    void from() {

        TranslationCostGenerateReportRequest.Mode response = TranslationCostGenerateReportRequest.Mode.from("no_match");
        assertEquals(TranslationCostGenerateReportRequest.Mode.NO_MATCH, response);

        TranslationCostGenerateReportRequest.Mode response2 = TranslationCostGenerateReportRequest.Mode.from("tm_match");
        assertEquals(TranslationCostGenerateReportRequest.Mode.TM_MATCH, response2);

    }

    @Test
    void to() {
        Object response = (TranslationCostGenerateReportRequest.Mode.TM_MATCH).to(TranslationCostGenerateReportRequest.Mode.TM_MATCH);
        assertEquals(response, "tm_match");

        Object response1 = (TranslationCostGenerateReportRequest.Mode.NO_MATCH).to(TranslationCostGenerateReportRequest.Mode.NO_MATCH);
        assertEquals(response1, "no_match");
    }

}