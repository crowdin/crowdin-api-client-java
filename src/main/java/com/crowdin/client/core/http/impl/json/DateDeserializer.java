package com.crowdin.client.core.http.impl.json;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends ValueDeserializer<Date> {

    @Override
    @SneakyThrows
    public Date deserialize(JsonParser p, DeserializationContext ctxt) {
        String date = p.getText();
        if (date == null || date.isEmpty()) {
            return null;
        }

        return deserializeDate(date);
    }

    @SneakyThrows
    public static Date deserializeDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return format.parse(date);
        } catch (Exception e) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(date);
        }
    }
}
