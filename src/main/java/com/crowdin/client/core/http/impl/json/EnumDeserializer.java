package com.crowdin.client.core.http.impl.json;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.DatabindException;
import com.crowdin.client.core.model.EnumConverter;
import lombok.SneakyThrows;


public class EnumDeserializer extends ValueDeserializer<Enum> {

    private JavaType type;

    public EnumDeserializer() {
    }

    public EnumDeserializer(JavaType type) {
        this.type = type;
    }

    @Override
    public ValueDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws DatabindException {
        //beanProperty is null when the type to deserialize is the top-level type or a generic type, not a type of a bean property
        JavaType type = deserializationContext.getContextualType() != null
                ? deserializationContext.getContextualType()
                : beanProperty.getMember().getType();
        return new EnumDeserializer(type);
    }

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctxt) {
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
