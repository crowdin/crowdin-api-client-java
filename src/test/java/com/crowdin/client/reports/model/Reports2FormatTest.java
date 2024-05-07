package com.crowdin.client.reports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Reports2FormatTest {

    @Test
    void from() {
        Reports2Format response = Reports2Format.from("xlsx");
        assertEquals(Reports2Format.XLSX, response);

        Reports2Format response1 = Reports2Format.from("csv");
        assertEquals(Reports2Format.CSV, response1);

    }

    @Test
    void to() {
        Object response = (Reports2Format.XLSX).to(Reports2Format.XLSX);
        assertEquals(response, "xlsx");

        Object response1 = (Reports2Format.CSV).to(Reports2Format.CSV);
        assertEquals(response1, "csv");
    }

}