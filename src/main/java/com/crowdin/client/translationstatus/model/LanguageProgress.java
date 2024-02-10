package com.crowdin.client.translationstatus.model;

import com.crowdin.client.languages.model.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LanguageProgress extends Progress {

    private String languageId;
    private Language language;
}
