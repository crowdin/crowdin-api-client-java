package crowdin.api.client.core.http.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import crowdin.api.client.core.http.JsonTransformer;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

public class JacksonJsonTransformer implements JsonTransformer {

    private final ObjectMapper objectMapper;

    public JacksonJsonTransformer() {
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+hh:mm"));
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
