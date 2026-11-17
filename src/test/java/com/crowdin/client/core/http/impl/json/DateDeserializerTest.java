package com.crowdin.client.core.http.impl.json;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DateDeserializerTest {

    @Test
    public void testDeserializeWithOffsetsInNonUtcTimeZone() {
        TimeZone original = TimeZone.getDefault();
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
            JacksonJsonTransformer transformer = new JacksonJsonTransformer();
            String[] dates = {
                "2019-07-11T07:40:22+00:00",
                "2019-07-11T09:40:22+02:00",
                "2019-07-11T03:40:22-04:00",
                "2019-07-11T07:40:22Z"
            };
            for (String date : dates) {
                assertEquals(Date.from(Instant.parse("2019-07-11T07:40:22Z")),
                        transformer.parse("\"" + date + "\"", Date.class), date);
            }
            assertEquals(Date.from(Instant.parse("2019-07-11T07:40:22.123Z")),
                    transformer.parse("\"2019-07-11T07:40:22.123+00:00\"", Date.class));
        } finally {
            TimeZone.setDefault(original);
        }
    }

    @Test
    public void testLegacyDatesAndEmptyValues() {
        TimeZone original = TimeZone.getDefault();
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
            Date expected = Date.from(Instant.parse("2019-07-11T11:40:22Z"));
            assertEquals(expected, DateDeserializer.deserializeDate("2019-07-11T07:40:22"));
            assertEquals(expected, DateDeserializer.deserializeDate("2019-07-11 07:40:22"));
            JacksonJsonTransformer transformer = new JacksonJsonTransformer();
            assertNull(transformer.parse("\"\"", Date.class));
            assertNull(transformer.parse("null", Date.class));
        } finally {
            TimeZone.setDefault(original);
        }
    }
}
