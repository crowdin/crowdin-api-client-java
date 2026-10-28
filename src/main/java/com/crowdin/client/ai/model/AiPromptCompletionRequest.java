package com.crowdin.client.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class AiPromptCompletionRequest {
    private AiPromptResource resources;

    /**
     * Legacy map view of the tools, as passed to {@code setTools(Map)}. It is never sent as is; the deprecated setter
     * converts it into {@link #aiTools}.
     *
     * @deprecated the API expects an array of tools; use {@link #aiTools} instead
     */
    @Deprecated
    @JsonIgnore
    private Map<String, Object> tools;

    /**
     * Tools available to the model (JSON key {@code tools}); each entry is
     * {@code {"tool": {"type": "function", "function": {"name": ..., "description": ..., "parameters": {...}}}}}
     */
    @JsonProperty("tools")
    private List<AiTool> aiTools;

    /**
     * @deprecated the API accepts either a string or an object; use {@link #toolChoice} instead
     */
    @Deprecated
    @JsonIgnore
    private Map<String, Object> tool_choice;

    /**
     * Either a {@link String} or an object (e.g. {@link Map})
     */
    @JsonProperty("tool_choice")
    private Object toolChoice;

    /**
     * Legacy setter for the tools. The map is converted into a single {@link #aiTools} entry when it has one of the
     * documented shapes:
     * <ul>
     *     <li>a tools array entry: {@code {"tool": {"type": "function", "function": {...}}}}</li>
     *     <li>the tool object itself: {@code {"type": "function", "function": {...}}}</li>
     * </ul>
     * where {@code function} contains {@code name} (required, string), and optionally {@code description} (string)
     * and {@code parameters} (object). An empty map sets an empty tools array and {@code null} clears the tools.
     *
     * @param tools legacy tools map
     * @throws IllegalArgumentException if the map has any other structure; nothing is changed in that case
     * @deprecated the API expects an array of tools; use {@code setAiTools(List)} ({@link #aiTools}) instead
     */
    @Deprecated
    public void setTools(Map<String, Object> tools) {
        this.aiTools = toAiTools(tools);
        this.tools = tools;
    }

    /**
     * @param tool_choice tool choice object
     * @deprecated use {@code setToolChoice(Object)} ({@link #toolChoice}) instead
     */
    @Deprecated
    public void setTool_choice(Map<String, Object> tool_choice) {
        this.tool_choice = tool_choice;
        this.toolChoice = tool_choice;
    }

    /**
     * @return {@link #toolChoice} if it is an object, otherwise {@code null}
     * @deprecated use {@code getToolChoice()} ({@link #toolChoice}) instead
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public Map<String, Object> getTool_choice() {
        if (this.toolChoice instanceof Map) {
            return (Map<String, Object>) this.toolChoice;
        }
        return null;
    }

    private static List<AiTool> toAiTools(Map<String, Object> tools) {
        if (tools == null) {
            return null;
        }
        if (tools.isEmpty()) {
            return new ArrayList<>();
        }
        Object tool;
        if (tools.size() == 1 && tools.containsKey("tool")) {
            tool = tools.get("tool");
        } else if (tools.containsKey("type") && tools.containsKey("function")) {
            tool = tools;
        } else {
            throw invalidTools("expected {\"tool\": {...}} or {\"type\": \"function\", \"function\": {...}}, got keys " + tools.keySet());
        }
        AiTool aiTool = new AiTool();
        aiTool.setTool(toTool(tool));
        return new ArrayList<>(Collections.singletonList(aiTool));
    }

    @SuppressWarnings("unchecked")
    private static Tool toTool(Object value) {
        if (!(value instanceof Map)) {
            throw invalidTools("\"tool\" must be an object");
        }
        Map<String, Object> map = (Map<String, Object>) value;
        requireOnlyKeys(map, "tool", "type", "function");
        if (!"function".equals(map.get("type"))) {
            throw invalidTools("\"type\" must be \"function\"");
        }
        if (!(map.get("function") instanceof Map)) {
            throw invalidTools("\"function\" must be an object");
        }
        Map<String, Object> function = (Map<String, Object>) map.get("function");
        requireOnlyKeys(function, "function", "name", "description", "parameters");
        Object name = function.get("name");
        if (!(name instanceof String) || ((String) name).isEmpty()) {
            throw invalidTools("\"function.name\" must be a non-empty string");
        }
        Object description = function.get("description");
        if (description != null && !(description instanceof String)) {
            throw invalidTools("\"function.description\" must be a string");
        }
        Object parameters = function.get("parameters");
        if (parameters != null && !(parameters instanceof Map)) {
            throw invalidTools("\"function.parameters\" must be an object");
        }
        ToolFunction toolFunction = new ToolFunction();
        toolFunction.setName((String) name);
        toolFunction.setDescription((String) description);
        toolFunction.setParameters((Map<String, Object>) parameters);
        Tool tool = new Tool();
        tool.setType("function");
        tool.setFunction(toolFunction);
        return tool;
    }

    private static void requireOnlyKeys(Map<String, Object> map, String name, String... allowed) {
        List<String> allowedKeys = Arrays.asList(allowed);
        for (String key : map.keySet()) {
            if (!allowedKeys.contains(key)) {
                throw invalidTools("unsupported key \"" + key + "\" in \"" + name + "\"");
            }
        }
    }

    private static IllegalArgumentException invalidTools(String reason) {
        return new IllegalArgumentException("Legacy tools map cannot be converted to the API tools array (" + reason
                + "); use setAiTools(List) instead");
    }

    @Data
    public static class AiTool {
        private Tool tool;
    }

    @Data
    public static class Tool {
        /**
         * Currently, only {@code function} is supported
         */
        private String type;
        private ToolFunction function;
    }

    @Data
    public static class ToolFunction {
        private String name;
        private String description;
        private Map<String, Object> parameters;
    }

    @Data
    public static class AiPromptResource {
        private Long projectId;
        private String sourceLanguageId;
        private String targetLanguageId;
        private List<Long> stringIds;
        /**
         * @deprecated not part of the API prompt completion resources
         */
        @Deprecated
        private List<Long> filteredStringsIds;
        private Map<String, Object> overridePromptValues;
        private String customInstruction;
        /**
         * @deprecated not part of the API prompt completion resources; pass it in {@link #overridePromptValues} instead
         */
        @Deprecated
        private String projectDescription;
    }
}
