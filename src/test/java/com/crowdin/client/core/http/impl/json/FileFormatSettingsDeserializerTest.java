package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.projectsgroups.model.*;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FileFormatSettingsDeserializerTest {
    private ObjectMapper objectMapper;
    private FileFormatSettingsDeserializer deserializer;

    private String deserializeObjectExample;

    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        deserializer = new FileFormatSettingsDeserializer(objectMapper);

        String errorResponseDir = "api/core/deserializeObjectExample.json";

        deserializeObjectExample = getFile(errorResponseDir);
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
    void deserializeValidJsonReturnsFileFormatSettingsResource() {
        // List of all formats in switch case
        List<String> formats = new ArrayList<>(Arrays.asList(
                "properties", "xml", "webxml", "html", "adoc",
                "android", "md", "mdxV1", "mdxV2", "fmMd", "fmHtml", "madcapFlsnp", "docx", "idml", "mif", "dita",
                "mediawiki", "arb", "json", "js", "fjs", "macosx", "chrome", "react_intl", "txt"
        ));


        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new SimpleModule().addDeserializer(
                        FileFormatSettingsResource.class, new FileFormatSettingsDeserializer(new ObjectMapper())))
                .build();

        formats.forEach(f -> {
            try {
                String newJson = replaceFormat(deserializeObjectExample, f);
                FileFormatSettingsResource result = mapper.readValue(newJson, FileFormatSettingsResource.class);
                assertion(f, result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void assertion(String format, FileFormatSettingsResource result) {
        assertNotNull(result);
        assertNotNull(result.getSettings());
        assertEquals(result.getId(), 123L);
        assertEquals(result.getName(), "Example Name");
        assertEquals(result.getFormat(), format);
    }

    @Test
    void deserializeNullParentNodeReturnsNull() {
        // Prepare a JSON object with null parent node
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.putNull("format"); // Simulating null parent node
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new SimpleModule().addDeserializer(
                        FileFormatSettingsResource.class, new FileFormatSettingsDeserializer(new ObjectMapper())))
                .build();
        String json = rootNode.toString();

        // Assertions
        assertThrows(Exception.class, () -> mapper.readValue(json, FileFormatSettingsResource.class));
    }

    public static String replaceFormat(String json, String newFormat) throws IOException {
        // Parse the JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        // Replace the value of the "format" field
        if (rootNode.has("format")) {
            String currentFormat = rootNode.get("format").asText();
            if (!currentFormat.equals(newFormat)) {
                ((ObjectNode) rootNode).put("format", newFormat);
            }
        }

        // Convert the modified JSON back to string
        return objectMapper.writeValueAsString(rootNode);
    }
}
