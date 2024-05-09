package com.crowdin.client.core.http.impl.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmptyArrayToNullDeserializerTest {

    private String errorsResponse;


    @BeforeEach
    void setUp() throws IOException {
        String errorResponseDir = "api/core/errorsResponse.json";

        errorsResponse = getFile(errorResponseDir);
    }

    private String getFile(String resourcePath) throws IOException {
        try (InputStream responseInputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (responseInputStream != null) {
                return new BufferedReader(new InputStreamReader(responseInputStream)).lines().collect(Collectors.joining("\n"));
            } else {
                throw new IOException("File not found: " + resourcePath);
            }
        }
    }

    @Test
    public void testDeserialize() throws IOException {
        // Mocking required objects
        JsonFactory jsonFactory = Mockito.mock(JsonFactory.class);
        JsonParser jsonParser = Mockito.mock(JsonParser.class);
        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);

        // Setting up behavior of mocked objects
        when(jsonFactory.createParser(errorsResponse)).thenReturn(jsonParser);
        when(jsonParser.getCurrentToken()).thenReturn(JsonToken.VALUE_NULL);
        when(deserializationContext.readValue(jsonParser, Object.class)).thenReturn("value");

        // Creating an instance of your deserializer
        EmptyArrayToNullDeserializer deserializer = new EmptyArrayToNullDeserializer();

        // Invoking the deserialize method
        Object result = deserializer.deserialize(jsonParser, deserializationContext);

        // Asserting the result
        assertNull(result);
    }

    @Test
    public void testDeserializeIfNotNull() throws IOException {
        // Mocking required objects
        JsonFactory jsonFactory = Mockito.mock(JsonFactory.class);
        JsonParser jsonParser = Mockito.mock(JsonParser.class);
        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create TypeFactory
        TypeFactory typeFactory = objectMapper.getTypeFactory();

        // Define a type: List<String>
        JavaType javaType = typeFactory.constructCollectionType(List.class, String.class);


        // Setting up behavior of mocked objects
        when(jsonFactory.createParser(errorsResponse)).thenReturn(jsonParser);
        when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
        when(deserializationContext.readValue(jsonParser, Object.class)).thenReturn("value");

        // Creating an instance of your deserializer
        EmptyArrayToNullDeserializer deserializer = new EmptyArrayToNullDeserializer(javaType);

        // Invoking the deserialize method
        Object result = deserializer.deserialize(jsonParser, deserializationContext);

        // Asserting the result
        assertNull(result);
    }

    @Test
    public void testDeserializeIfNotList() throws IOException {
        // Mocking required objects
        JsonFactory jsonFactory = Mockito.mock(JsonFactory.class);
        JsonParser jsonParser = Mockito.mock(JsonParser.class);
        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);
        // Create ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Create TypeFactory
        TypeFactory typeFactory = objectMapper.getTypeFactory();

        String[] strings = new String[5];
        // Define a type: List<String>
        JavaType javaType = typeFactory.constructType(strings.getClass());


        // Setting up behavior of mocked objects
        when(jsonFactory.createParser(errorsResponse)).thenReturn(jsonParser);
        when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
        when(deserializationContext.readValue(jsonParser, Object.class)).thenReturn("value");

        // Creating an instance of your deserializer
        EmptyArrayToNullDeserializer deserializer = new EmptyArrayToNullDeserializer(javaType);

        // Invoking the deserialize method
        Object result = deserializer.deserialize(jsonParser, deserializationContext);

        // Asserting the result
        assertNull(result);
    }

    @Test
    public void testDeserializeIfNull() throws IOException {
        // Mocking required objects
        JsonFactory jsonFactory = Mockito.mock(JsonFactory.class);
        JsonParser jsonParser = Mockito.mock(JsonParser.class);
        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);


        // Setting up behavior of mocked objects
        when(jsonFactory.createParser(errorsResponse)).thenReturn(jsonParser);
        when(jsonParser.getCurrentToken()).thenReturn(JsonToken.START_ARRAY);
        when(deserializationContext.readValue(jsonParser, Object.class)).thenReturn("value");

        // Creating an instance of your deserializer
        EmptyArrayToNullDeserializer deserializer = new EmptyArrayToNullDeserializer();

        // Invoking the deserialize method
        Object result = deserializer.deserialize(jsonParser, deserializationContext);

        // Asserting the result
        assertNull(result);
    }

}
