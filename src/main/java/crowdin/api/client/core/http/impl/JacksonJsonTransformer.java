package crowdin.api.client.core.http.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import crowdin.api.client.core.http.JsonTransformer;
import crowdin.api.client.core.model.PatchOperation;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        List<PatchOperation> list = Arrays.asList(
                PatchOperation.ADD,
                PatchOperation.COPY
        );
        JacksonJsonTransformer jacksonJsonTransformer = new JacksonJsonTransformer();
        String convert = jacksonJsonTransformer.convert(list);
        System.out.println(convert);
        List<PatchOperation> res = Arrays.asList(jacksonJsonTransformer.parse(convert, PatchOperation[].class));
        System.out.println(res);
    }
}
