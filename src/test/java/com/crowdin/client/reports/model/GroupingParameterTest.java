package com.crowdin.client.reports.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupingParameterTest {

    @Test
    void from() {
        GroupingParameter response = GroupingParameter.from("user");
        assertEquals(GroupingParameter.USER, response);

        GroupingParameter response1 = GroupingParameter.from("language");
        assertEquals(GroupingParameter.LANGUAGE, response1);
    }

    @Test
    void to() {
        Object response = (GroupingParameter.USER).to(GroupingParameter.USER);
        assertEquals(response, "user");

        Object response1 = (GroupingParameter.LANGUAGE).to(GroupingParameter.LANGUAGE);
        assertEquals(response1, "language");
    }
}