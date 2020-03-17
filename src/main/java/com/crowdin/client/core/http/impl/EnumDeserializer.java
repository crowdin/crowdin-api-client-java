package com.crowdin.client.core.http.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.crowdin.client.core.model.EnumConverter;
import lombok.SneakyThrows;

import java.io.IOException;

public class EnumDeserializer extends JsonDeserializer<Enum> implements ContextualDeserializer {

    private JavaType type;

    public EnumDeserializer() {
    }

    public EnumDeserializer(JavaType type) {
        this.type = type;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        //beanProperty is null when the type to deserialize is the top-level type or a generic type, not a type of a bean property
        JavaType type = deserializationContext.getContextualType() != null
                ? deserializationContext.getContextualType()
                : beanProperty.getMember().getType();
        return new EnumDeserializer(type);
    }

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = p.getText();
        return this.deserialize((Class<? extends Enum>) this.type.getRawClass(), text);
    }


    @SneakyThrows
    private Enum deserialize(Class<? extends Enum> type, String value) {
        if (EnumConverter.class.isAssignableFrom(type)) {
            return (Enum) type.getMethod("from", String.class).invoke(null, value);
        } else {
            return Enum.valueOf(type, value);
        }
    }
}
