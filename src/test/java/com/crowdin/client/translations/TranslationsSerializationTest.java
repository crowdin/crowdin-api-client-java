package com.crowdin.client.translations;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.translations.model.Alignment;
import com.crowdin.client.translations.model.ApplyPreTranslationStringsBasedRequest;
import com.crowdin.client.translations.model.AutoApproveOption;
import com.crowdin.client.translations.model.LabelMatchRule;
import com.crowdin.client.translations.model.PreTranslationStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("deprecation")
class TranslationsSerializationTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void fallbackLanguagesMapIsSentAsObjectTest() {
        ApplyPreTranslationStringsBasedRequest request = new ApplyPreTranslationStringsBasedRequest();
        request.setFallbackLanguagesMap(Collections.singletonMap("uk", Arrays.asList("ru", "pl")));
        assertEquals("{\"fallbackLanguages\":{\"uk\":[\"ru\",\"pl\"]}}", jsonTransformer.convert(request));
    }

    @Test
    void legacyFallbackLanguagesListIsSentAsIn137Test() {
        ApplyPreTranslationStringsBasedRequest.FallbackLanguage legacy = new ApplyPreTranslationStringsBasedRequest.FallbackLanguage();
        legacy.setLanguageId("uk");
        ApplyPreTranslationStringsBasedRequest request = new ApplyPreTranslationStringsBasedRequest();
        request.setFallbackLanguages(Collections.singletonList(legacy));
        assertEquals("{\"fallbackLanguages\":[{\"languageId\":\"uk\"}]}", jsonTransformer.convert(request));

        request.setFallbackLanguagesMap(Collections.singletonMap("uk", Collections.singletonList("ru")));
        assertEquals("{\"fallbackLanguages\":{\"uk\":[\"ru\"]}}", jsonTransformer.convert(request));

        assertEquals("{}", jsonTransformer.convert(new ApplyPreTranslationStringsBasedRequest()));
    }

    @Test
    void newEnumValuesTest() {
        ApplyPreTranslationStringsBasedRequest request = new ApplyPreTranslationStringsBasedRequest();
        request.setAutoApproveOption(AutoApproveOption.PERFECT_MATCH_APPROVED_ONLY);
        request.setLabelMatchRule(LabelMatchRule.ALL);
        request.setExcludeLabelMatchRule(LabelMatchRule.ANY);
        assertEquals("{\"autoApproveOption\":\"perfectMatchApprovedOnly\",\"labelMatchRule\":\"all\",\"excludeLabelMatchRule\":\"any\"}",
                jsonTransformer.convert(request));
    }

    @Test
    void unknownEnumValuesInResponseAreNullTest() {
        PreTranslationStatus status = jsonTransformer.parse("{\"identifier\":\"x\",\"attributes\":{\"scope\":\"somethingNew\","
                + "\"replaceTranslationsOption\":\"somethingNew\"}}", PreTranslationStatus.class);
        assertNull(status.getAttributes().getScope());
        assertNull(status.getAttributes().getReplaceTranslationsOption());
    }

    @Test
    void alignmentFractionalProbabilityTest() {
        Alignment alignment = jsonTransformer.parse("{\"sourceWord\":\"a\",\"probability\":0.85}", Alignment.class);
        assertEquals(Double.valueOf(0.85), alignment.getProbabilityValue());
        assertEquals(Integer.valueOf(0), alignment.getProbability());

        Alignment legacy = new Alignment();
        legacy.setProbability(1);
        assertEquals(Double.valueOf(1.0), legacy.getProbabilityValue());
        assertEquals("{\"probability\":1.0}", jsonTransformer.convert(legacy));
    }
}
