package com.crowdin.client.core.http.impl.json;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.SerializationContext;
import com.crowdin.client.core.model.EnumConverter;
import lombok.SneakyThrows;


public class EnumSerializer extends ValueSerializer<Enum> {
    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializationContext serializers) {
        Object val;
        if (value instanceof EnumConverter) {
            val = this.serialize(value, EnumConverter.class.cast(value));
        } else {
            val = value.name();
        }
        if (val instanceof Integer) {
            gen.writeNumber((Integer) val);
        } else {
            gen.writeString(val.toString());
        }
    }

    @SneakyThrows
    private Object serialize(Enum value, EnumConverter converter) {
        return converter.to(value);
    }
}
