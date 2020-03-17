package com.crowdin.client.core.http.impl;

import com.crowdin.client.core.http.JsonTransformer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class JacksonJsonTransformer implements JsonTransformer {

    private final ObjectMapper objectMapper;

    public JacksonJsonTransformer() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Enum.class, new EnumSerializer());
        module.addDeserializer(Enum.class, new EnumDeserializer());
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+hh:mm"))
                .registerModule(module);
    }

    @Override
    @SneakyThrows
    public <T> T parse(String json, Class<T> clazz) {
        return this.objectMapper.readValue(json, clazz);
    }

    @Override
    @SneakyThrows
    public <T> String convert(T obj) {
        return this.objectMapper.writeValueAsString(obj);
    }

}
