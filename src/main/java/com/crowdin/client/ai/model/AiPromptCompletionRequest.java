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
     * @deprecated the API expects an array of tools; use {@link #aiTools} instead. This field is not sent to the API.
     */
    @Deprecated
    @JsonIgnore
    private Map<String, Object> tools;

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
     * @deprecated use {@link #toolChoice} instead
     */
    @Deprecated
    public void setTool_choice(Map<String, Object> tool_choice) {
        this.tool_choice = tool_choice;
        this.toolChoice = tool_choice;
    }

    /**
     * @deprecated use {@link #toolChoice} instead
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
