package com.crowdin.client.reports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelIncludeTypeTest {

    @Test
    void from() {
        LabelIncludeType response = LabelIncludeType.from("strings_with_label");
        assertEquals(LabelIncludeType.STRINGS_WITH_LABEL, response);

        LabelIncludeType response1 = LabelIncludeType.from("strings_without_label");
        assertEquals(LabelIncludeType.STRINGS_WITHOUT_LABEL, response1);
    }

    @Test
    void to() {
        Object response = (LabelIncludeType.STRINGS_WITH_LABEL).to(LabelIncludeType.STRINGS_WITH_LABEL);
        assertEquals(response, "strings_with_label");

        Object response1 = (LabelIncludeType.STRINGS_WITHOUT_LABEL).to(LabelIncludeType.STRINGS_WITHOUT_LABEL);
        assertEquals(response1, "strings_without_label");
    }
}