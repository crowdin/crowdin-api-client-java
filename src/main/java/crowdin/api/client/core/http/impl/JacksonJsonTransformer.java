package crowdin.api.client.core.http.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import crowdin.api.client.core.http.JsonTransformer;
import lombok.SneakyThrows;

public class JacksonJsonTransformer implements JsonTransformer {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
