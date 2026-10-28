package com.crowdin.client.ai.model;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("deprecation")
class AiPromptCompletionRequestToolsTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void legacyToolsArrayEntryIsConvertedTest() {
        Map<String, Object> legacy = map("tool", map("type", "function",
                "function", map("name", "lookup", "description", "Looks up a term", "parameters", map("type", "object"))));

        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setTools(legacy);

        assertSame(legacy, request.getTools());
        assertEquals(1, request.getAiTools().size());
        AiPromptCompletionRequest.ToolFunction function = request.getAiTools().get(0).getTool().getFunction();
        assertEquals("function", request.getAiTools().get(0).getTool().getType());
        assertEquals("lookup", function.getName());
        assertEquals("Looks up a term", function.getDescription());
        assertEquals(map("type", "object"), function.getParameters());
        assertJsonEquals("{\"tools\":[{\"tool\":{\"type\":\"function\",\"function\":{\"name\":\"lookup\","
                + "\"description\":\"Looks up a term\",\"parameters\":{\"type\":\"object\"}}}}]}", jsonTransformer.convert(request));
    }

    @Test
    void legacyToolObjectIsConvertedTest() {
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setTools(map("type", "function", "function", map("name", "lookup")));

        assertJsonEquals("{\"tools\":[{\"tool\":{\"type\":\"function\",\"function\":{\"name\":\"lookup\"}}}]}",
                jsonTransformer.convert(request));
    }

    @Test
    void legacyNullAndEmptyToolsTest() {
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setTools(Collections.emptyMap());
        assertTrue(request.getAiTools().isEmpty());
        assertEquals("{\"tools\":[]}", jsonTransformer.convert(request));

        request.setTools(null);
        assertNull(request.getTools());
        assertNull(request.getAiTools());
        assertEquals("{}", jsonTransformer.convert(request));
    }

    @Test
    void invalidLegacyToolsAreRejectedTest() {
        List<Map<String, Object>> invalid = Arrays.asList(
                map("name", "lookup"),
                map("tool", "lookup"),
                map("tool", map("type", "function", "function", map("name", "a")), "extra", 1),
                map("tool", map("type", "retrieval", "function", map("name", "a"))),
                map("tool", map("type", "function")),
                map("tool", map("type", "function", "function", "lookup")),
                map("tool", map("type", "function", "function", map("description", "no name"))),
                map("tool", map("type", "function", "function", map("name", ""))),
                map("tool", map("type", "function", "function", map("name", 5))),
                map("tool", map("type", "function", "function", map("name", "a", "description", 1))),
                map("tool", map("type", "function", "function", map("name", "a", "parameters", "{}"))),
                map("tool", map("type", "function", "function", map("name", "a", "strict", true))),
                map("tool", map("type", "function", "function", map("name", "a"), "id", "x")),
                map("type", "function", "function", map("name", "a"), "tool", map())
        );
        for (Map<String, Object> tools : invalid) {
            AiPromptCompletionRequest request = new AiPromptCompletionRequest();
            AiPromptCompletionRequest.AiTool existing = new AiPromptCompletionRequest.AiTool();
            List<AiPromptCompletionRequest.AiTool> existingTools = Collections.singletonList(existing);
            request.setAiTools(existingTools);

            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> request.setTools(tools), tools.toString());
            assertTrue(e.getMessage().contains("setAiTools"), e.getMessage());
            assertSame(existingTools, request.getAiTools(), "state must not change: " + tools);
            assertNull(request.getTools());
        }
    }

    @Test
    void newToolsApiIsUnaffectedTest() {
        AiPromptCompletionRequest.ToolFunction function = new AiPromptCompletionRequest.ToolFunction();
        function.setName("lookup");
        AiPromptCompletionRequest.Tool tool = new AiPromptCompletionRequest.Tool();
        tool.setType("function");
        tool.setFunction(function);
        AiPromptCompletionRequest.AiTool aiTool = new AiPromptCompletionRequest.AiTool();
        aiTool.setTool(tool);
        AiPromptCompletionRequest request = new AiPromptCompletionRequest();
        request.setAiTools(Collections.singletonList(aiTool));
        request.setToolChoice("auto");

        assertNull(request.getTools());
        assertJsonEquals("{\"tools\":[{\"tool\":{\"type\":\"function\",\"function\":{\"name\":\"lookup\"}}}],\"tool_choice\":\"auto\"}",
                jsonTransformer.convert(request));
    }

    private static Map<String, Object> map(Object... keyValues) {
        Map<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < keyValues.length; i += 2) {
            map.put((String) keyValues[i], keyValues[i + 1]);
        }
        return map;
    }

    @SneakyThrows
    private void assertJsonEquals(String expected, String actual) {
        assertEquals(objectMapper.readTree(expected), objectMapper.readTree(actual), actual);
    }
}
