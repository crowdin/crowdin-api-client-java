package com.crowdin.client.core.http.impl.json;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.databind.*;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.util.Collection;

public class EmptyArrayToNullDeserializer extends StdDeserializer<Object> {
    private JavaType type;

    public EmptyArrayToNullDeserializer() {
        super(Object.class);
    }

    public EmptyArrayToNullDeserializer(JavaType type) {
        super(type.getRawClass());
        this.type = type;
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) {
        if (p.currentToken() == JsonToken.VALUE_NULL) {
            return null;
        }

        Class<?> clazz = this.type != null ? this.type.getRawClass() : Object.class;

        if (p.currentToken() == JsonToken.START_ARRAY) {
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
    public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        return new EmptyArrayToNullDeserializer(property.getType());
    }
}
