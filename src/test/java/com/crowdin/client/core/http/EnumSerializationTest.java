package com.crowdin.client.core.http;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.EscapeQuotesMode;
import com.crowdin.client.core.model.EscapeSpecialCharsMode;
import com.crowdin.client.core.model.JsonFileType;
import com.crowdin.client.sourcefiles.model.ExportQuotes;
import com.crowdin.client.webhooks.model.OrganizationEvent;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumSerializationTest {

    private final JsonTransformer jsonTransformer;

    public EnumSerializationTest() {
        this.jsonTransformer = new JacksonJsonTransformer();
    }

    //<editor-fold desc="Numeric enums">

    @Test
    public void escapeQuotesMode() {
        serializeAndCompare(EscapeQuotesMode.DO_NOT_ESCAPE_SINGLE_QUOTE, 0);
        serializeAndCompare(EscapeQuotesMode.ESCAPE_SINGLE_QUOTE_BY_ANOTHER_SINGLE_QUOTE, 1);
        serializeAndCompare(EscapeQuotesMode.ESCAPE_SINGLE_QUOTE_BY_BACK_SLASH, 2);
        serializeAndCompare(EscapeQuotesMode.ESCAPE_SINGLE_QUOTE_BY_ANOTHER_SINGLE_QUOTE_ONLY_IF_VARIABLES, 3);

        deserializeAndCompare(0, EscapeQuotesMode.DO_NOT_ESCAPE_SINGLE_QUOTE);
        deserializeAndCompare(1, EscapeQuotesMode.ESCAPE_SINGLE_QUOTE_BY_ANOTHER_SINGLE_QUOTE);
        deserializeAndCompare(2, EscapeQuotesMode.ESCAPE_SINGLE_QUOTE_BY_BACK_SLASH);
        deserializeAndCompare(3, EscapeQuotesMode.ESCAPE_SINGLE_QUOTE_BY_ANOTHER_SINGLE_QUOTE_ONLY_IF_VARIABLES);
    }

    @Test
    public void escapeSpecialCharsMode() {
        serializeAndCompare(EscapeSpecialCharsMode.DO_NOT_ESCAPE, 0);
        serializeAndCompare(EscapeSpecialCharsMode.ESCAPE_BY_BACKSLASH, 1);

        deserializeAndCompare(0, EscapeSpecialCharsMode.DO_NOT_ESCAPE);
        deserializeAndCompare(1, EscapeSpecialCharsMode.ESCAPE_BY_BACKSLASH);
    }

    //</editor-fold>

    //<editor-fold desc="String enums">

    @Test
    public void jsonFileType() {
        serializeAndCompare(JsonFileType.I18NEXT_JSON, "i18next_json");
        serializeAndCompare(JsonFileType.NESTJS_I18N, "nestjs_i18n");

        deserializeAndCompare("i18next_json", JsonFileType.I18NEXT_JSON);
        deserializeAndCompare("nestjs_i18n", JsonFileType.NESTJS_I18N);
    }

    @Test
    public void exportQuotes() {
        serializeAndCompare(ExportQuotes.DOUBLE, "double");
        serializeAndCompare(ExportQuotes.SINGLE, "single");

        deserializeAndCompare("double", ExportQuotes.DOUBLE);
        deserializeAndCompare("single", ExportQuotes.SINGLE);
    }

    @Test
    public void webhooks_OrganizationEvent() {
        serializeAndCompare(OrganizationEvent.GROUP_CREATED, "group.created");
        serializeAndCompare(OrganizationEvent.GROUP_DELETED, "group.deleted");
        serializeAndCompare(OrganizationEvent.PROJECT_CREATED, "project.created");
        serializeAndCompare(OrganizationEvent.PROJECT_DELETED, "project.deleted");

        deserializeAndCompare("group.created", OrganizationEvent.GROUP_CREATED);
        deserializeAndCompare("group.deleted", OrganizationEvent.GROUP_DELETED);
        deserializeAndCompare("project.created", OrganizationEvent.PROJECT_CREATED);
        deserializeAndCompare("project.deleted", OrganizationEvent.PROJECT_DELETED);
    }

    //</editor-fold>

    @SneakyThrows
    private <T extends Enum<T>> void serializeAndCompare(T enumValue, Object expectedEnumValue) {
        String enumValueString = jsonTransformer.convert(enumValue);
        assertEquals(expectedEnumValue.toString(), enumValueString.replace("\"", ""));
    }

    @SneakyThrows
    private <T extends Enum<T>> void deserializeAndCompare(Object value, T expectedEnumValue) {
        if (value instanceof String) {
            value = "\"" + value + "\"";
        }

        Object actualEnumValue = jsonTransformer.parse(value.toString(), expectedEnumValue.getClass());
        assertEquals(expectedEnumValue, actualEnumValue);
    }
}
