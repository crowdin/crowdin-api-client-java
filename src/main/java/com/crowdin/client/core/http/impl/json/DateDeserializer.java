package com.crowdin.client.core.http.impl.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    @SneakyThrows
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        if (date == null || date.isEmpty()) {
            return null;
        }

        return deserializeDate(date);
    }

    @SneakyThrows
    public static Date deserializeDate(String date) {
        try {
            return Date.from(OffsetDateTime.parse(date).toInstant());
        } catch (DateTimeParseException e) {
            // Preserve support for legacy timestamps without an offset.
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return format.parse(date);
        } catch (Exception e) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(date);
        }
    }
}
