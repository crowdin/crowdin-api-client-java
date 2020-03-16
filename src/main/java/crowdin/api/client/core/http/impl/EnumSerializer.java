package crowdin.api.client.core.http.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import crowdin.api.client.core.model.EnumConverter;
import lombok.SneakyThrows;

import java.io.IOException;

public class EnumSerializer extends JsonSerializer<Enum> {
    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String str;
        if (value instanceof EnumConverter) {
            str = this.serialize(value, EnumConverter.class.cast(value));
        } else {
            str = value.name();
        }
        gen.writeString(str);
    }

    @SneakyThrows
    private String serialize(Enum value, EnumConverter converter) {
        return converter.to(value);
    }
}
