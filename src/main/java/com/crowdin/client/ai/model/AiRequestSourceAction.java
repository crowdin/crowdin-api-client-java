package com.crowdin.client.ai.model;

import com.crowdin.client.core.model.EnumConverter;

public enum AiRequestSourceAction implements EnumConverter<AiRequestSourceAction> {
    AI_PROXY("ai_proxy"),
    AI_GATEWAY("ai_gateway"),
    AI_TRANSLATE_STRINGS("ai_translate_strings"),
    AI_FILE_TRANSLATE("ai_file_translate"),
    AI_PROMPT_COMPLETION("ai_prompt_completion"),
    PRE_TRANSLATE_MANUAL("pre_translate:manual"),
    PRE_TRANSLATE_WORKFLOW("pre_translate:workflow"),
    AI_ALIGNMENT("ai_alignment"),
    QA_CHECK("qa_check"),
    AI_SUGGESTION("ai_suggestion"),
    ADVISOR("advisor");

    private final String value;

    AiRequestSourceAction(String value) {
        this.value = value;
    }

    public static AiRequestSourceAction from(String value) {
        for (AiRequestSourceAction sourceAction : values()) {
            if (sourceAction.value.equals(value)) {
                return sourceAction;
            }
        }
        throw new IllegalArgumentException("Unknown AI request source action: " + value);
    }

    @Override
    public String to(AiRequestSourceAction sourceAction) {
        return sourceAction.value;
    }
}
