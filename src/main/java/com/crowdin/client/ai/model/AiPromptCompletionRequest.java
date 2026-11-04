package com.crowdin.client.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AiPromptCompletionRequest {
    private AiPromptResource resources;

    /**
     * Legacy tools map. Values set through {@code setTools(Map)} are only stored and returned by {@code getTools()};
     * they are <b>not sent to the API</b>.
     *
     * @deprecated the API expects an array of tools; use {@code setAiTools(List)} ({@link #aiTools}) instead
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
