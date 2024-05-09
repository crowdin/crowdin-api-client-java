package com.crowdin.client.core.http.impl.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Collection;

public class EmptyArrayToNullDeserializer extends StdDeserializer<Object> implements ContextualDeserializer {
    private JavaType type;

    public EmptyArrayToNullDeserializer() {
        super(Object.class);
    }

    public EmptyArrayToNullDeserializer(JavaType type) {
        super(type.getRawClass());
        this.type = type;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }

        Class<?> clazz = this.type != null ? this.type.getRawClass() : Object.class;

        if (p.getCurrentToken() == JsonToken.START_ARRAY) {
            if (!isCollectionType(clazz)) {
                p.nextToken();
                return null;
            } else {
                return ctxt.readValue(p, clazz);
            }
        }

        return ctxt.readValue(p, clazz);
    }

    private static boolean isCollectionType(Class<?> type) {
        return type.isArray() || Collection.class.isAssignableFrom(type);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        return new EmptyArrayToNullDeserializer(property.getType());
    }
}
