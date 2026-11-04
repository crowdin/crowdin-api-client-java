package com.crowdin.client.ai.model;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code setTools(Map)} keeps its SDK 1.37.0 behavior: the map is only stored and is never sent. The documented
 * {@code tools} array is sent through {@code setAiTools(List)}.
 */
@SuppressWarnings("deprecation")
class AiPromptCompletionRequestToolsTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void legacyToolsMapIsStoredButNotSentTest() {
        Map<String, Object> legacy = new LinkedHashMap<>();
        legacy.put("tool", Collections.singletonMap("type", "function"));
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setTools(legacy);

        assertSame(legacy, request.getTools());
        assertNull(request.getAiTools());
        assertEquals("{}", jsonTransformer.convert(request));

        request.setTools(Collections.emptyMap());
        assertTrue(request.getTools().isEmpty());
        assertEquals("{}", jsonTransformer.convert(request));

        request.setTools(null);
        assertNull(request.getTools());
        assertEquals("{}", jsonTransformer.convert(request));
    }

    @Test
    void anyLegacyMapShapeIsAcceptedWithoutValidationTest() {
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        Map<String, Object> unexpected = Collections.singletonMap("ignored", "value");
        request.setTools(unexpected);
        assertSame(unexpected, request.getTools());
        assertEquals("{}", jsonTransformer.convert(request));
    }

    @Test
    void aiToolsAreSentAsTheDocumentedArrayTest() {
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setAiTools(Collections.singletonList(tool("get_weather", "Weather", Collections.singletonMap("type", "object"))));

        assertJsonEquals("{\"tools\":[{\"tool\":{\"type\":\"function\",\"function\":{\"name\":\"get_weather\","
                + "\"description\":\"Weather\",\"parameters\":{\"type\":\"object\"}}}}]}", jsonTransformer.convert(request));

        request.setAiTools(Collections.emptyList());
        assertEquals("{\"tools\":[]}", jsonTransformer.convert(request));
    }

    @Test
    void legacyMapAndAiToolsProduceOneToolsPropertyTest() {
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setTools(Collections.singletonMap("tool", Collections.singletonMap("type", "function")));
        request.setAiTools(Collections.singletonList(tool("f", null, null)));
        String json = jsonTransformer.convert(request);

        assertEquals(1, json.split("\"tools\"", -1).length - 1, json);
        assertJsonEquals("{\"tools\":[{\"tool\":{\"type\":\"function\",\"function\":{\"name\":\"f\"}}}]}", json);
        assertEquals(1, request.getAiTools().size());
        assertEquals(1, request.getTools().size());
    }

    @Test
    void parsedRequestUsesAiToolsOnlyTest() {
        AiPromptCompletionRequest parsed = jsonTransformer.parse(
                "{\"tools\":[{\"tool\":{\"type\":\"function\",\"function\":{\"name\":\"f\"}}}]}", AiPromptCompletionRequest.class);
        List<AiPromptCompletionRequest.AiTool> tools = parsed.getAiTools();
        assertEquals("f", tools.get(0).getTool().getFunction().getName());
        assertNull(parsed.getTools());
        assertFalse(jsonTransformer.convert(parsed).contains("ignored"));
    }

    private static AiPromptCompletionRequest.AiTool tool(String name, String description, Map<String, Object> parameters) {
        AiPromptCompletionRequest.ToolFunction function = new AiPromptCompletionRequest.ToolFunction();
        function.setName(name);
        function.setDescription(description);
        function.setParameters(parameters);
        AiPromptCompletionRequest.Tool tool = new AiPromptCompletionRequest.Tool();
        tool.setType("function");
        tool.setFunction(function);
        AiPromptCompletionRequest.AiTool aiTool = new AiPromptCompletionRequest.AiTool();
        aiTool.setTool(tool);
        return aiTool;
    }

    @SneakyThrows
    private void assertJsonEquals(String expected, String actual) {
        JsonNode e = objectMapper.readTree(expected);
        JsonNode a = objectMapper.readTree(actual);
        assertEquals(e, a, actual);
    }
}
