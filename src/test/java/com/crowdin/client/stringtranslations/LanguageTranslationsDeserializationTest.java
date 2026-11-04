package com.crowdin.client.stringtranslations;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.stringtranslations.model.AssetLanguageTranslations;
import com.crowdin.client.stringtranslations.model.ICULanguageTranslations;
import com.crowdin.client.stringtranslations.model.LanguageTranslations;
import com.crowdin.client.stringtranslations.model.LanguageTranslationsResponseObject;
import com.crowdin.client.stringtranslations.model.PlainLanguageTranslations;
import com.crowdin.client.stringtranslations.model.PluralLanguageTranslations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * List Language Translations returns plain, plural, ICU or asset translations; the model is chosen by {@code contentType}.
 */
class LanguageTranslationsDeserializationTest {

    private static final String PROVIDER_INFO = "\"provider\":\"ai\",\"providerId\":17,\"isPreTranslated\":true,\"matchRate\":75,\"matchType\":\"fuzzy\"";

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void icuWithAllFieldsTest() {
        ICULanguageTranslations icu = assertInstanceOf(ICULanguageTranslations.class, parse("{\"stringId\":1,\"translationId\":2,"
                + "\"contentType\":\"application/vnd.crowdin.text+icu\",\"text\":\"{count, plural, other {#}}\","
                + "\"user\":{\"id\":19,\"username\":\"john_doe\"},\"createdAt\":\"2019-09-23T11:26:54+00:00\","
                + PROVIDER_INFO + ",\"qaIssuesStatus\":\"failed\"}"));
        assertEquals(Long.valueOf(1), icu.getStringId());
        assertEquals(Long.valueOf(2), icu.getTranslationId());
        assertEquals("{count, plural, other {#}}", icu.getText());
        assertEquals("john_doe", icu.getUser().getUsername());
        assertEquals("ai", icu.getProvider());
        assertEquals(Long.valueOf(17), icu.getProviderId());
        assertEquals(Boolean.TRUE, icu.getIsPreTranslated());
        assertEquals(Integer.valueOf(75), icu.getMatchRate());
        assertEquals("fuzzy", icu.getMatchType());
        assertEquals("failed", icu.getQaIssuesStatus());
    }

    @Test
    void icuWithNullAndMissingOptionalFieldsTest() {
        ICULanguageTranslations nulls = assertInstanceOf(ICULanguageTranslations.class, parse("{\"stringId\":1,"
                + "\"contentType\":\"application/vnd.crowdin.text+icu\",\"text\":\"x\",\"provider\":null,\"providerId\":null,"
                + "\"matchRate\":null,\"matchType\":null}"));
        assertNull(nulls.getProvider());
        assertNull(nulls.getProviderId());
        assertNull(nulls.getMatchRate());
        assertNull(nulls.getMatchType());

        ICULanguageTranslations missing = assertInstanceOf(ICULanguageTranslations.class,
                parse("{\"stringId\":1,\"contentType\":\"application/vnd.crowdin.text+icu\",\"text\":\"x\"}"));
        assertNull(missing.getProvider());
        assertNull(missing.getIsPreTranslated());
        assertNull(missing.getQaIssuesStatus());
        assertNull(missing.getUser());
    }

    @Test
    void plainAndPluralProviderInfoTest() {
        PlainLanguageTranslations plain = assertInstanceOf(PlainLanguageTranslations.class,
                parse("{\"stringId\":1,\"contentType\":\"text/plain\",\"text\":\"Hi\"," + PROVIDER_INFO + "}"));
        assertEquals("ai", plain.getProvider());
        assertEquals("fuzzy", plain.getMatchType());

        PluralLanguageTranslations plural = assertInstanceOf(PluralLanguageTranslations.class,
                parse("{\"stringId\":1,\"contentType\":\"application/vnd.crowdin.text+plural\",\"qaIssuesStatus\":\"passed\","
                        + "\"plurals\":[{\"translationId\":2,\"text\":\"one\",\"pluralForm\":\"one\"," + PROVIDER_INFO + "}]}"));
        assertEquals("passed", plural.getQaIssuesStatus());
        PluralLanguageTranslations.Plurals form = plural.getPlurals().get(0);
        assertEquals("one", form.getPluralForm());
        assertEquals("ai", form.getProvider());
        assertEquals(Long.valueOf(17), form.getProviderId());
        assertEquals(Boolean.TRUE, form.getIsPreTranslated());
        assertEquals(Integer.valueOf(75), form.getMatchRate());
        assertEquals("fuzzy", form.getMatchType());
    }

    @Test
    void assetIsDetectedByShapeForAnyMimeTypeTest() {
        for (String mime : new String[]{"image/jpeg", "image/png", "video/mp4", "application/pdf"}) {
            AssetLanguageTranslations asset = assertInstanceOf(AssetLanguageTranslations.class, parse("{\"stringId\":1,"
                    + "\"translationId\":2,\"contentType\":\"" + mime + "\",\"url\":\"https://example.com/a\",\"qaIssuesStatus\":\"passed\"}"), mime);
            assertEquals(mime, asset.getContentType());
            assertEquals("https://example.com/a", asset.getUrl());
        }
    }

    @Test
    void unknownContentTypeIsRejectedTest() {
        String[] unknown = {
                "{\"stringId\":1,\"contentType\":\"text/markdown\",\"text\":\"**x**\"}",
                "{\"stringId\":1,\"contentType\":\"application/vnd.crowdin.text+new\",\"text\":\"x\"}",
                "{\"stringId\":1,\"contentType\":\"application/vnd.crowdin.text+new\",\"plurals\":[]}",
                "{\"stringId\":1,\"contentType\":\"image/jpeg\",\"url\":\"u\",\"text\":\"x\"}",
                "{\"stringId\":1,\"contentType\":\"image/jpeg\"}",
                "{\"stringId\":1,\"contentType\":null,\"text\":\"x\"}",
        };
        for (String json : unknown) {
            Exception e = assertThrows(Exception.class, () -> parse(json), json);
            assertTrue(rootMessage(e).startsWith("Wrong type: "), json + " -> " + rootMessage(e));
        }
        Exception missing = assertThrows(Exception.class, () -> parse("{\"stringId\":1,\"text\":\"x\"}"));
        assertEquals("Couldn't get class", rootMessage(missing));
    }

    @Test
    void imageAssetsAreRecognizedTest() {
        for (String mime : new String[]{"image/jpeg", "image/png", "image/svg+xml"}) {
            AssetLanguageTranslations asset = assertInstanceOf(AssetLanguageTranslations.class,
                    parse("{\"stringId\":1,\"contentType\":\"" + mime + "\",\"url\":\"https://example.com/a\"}"), mime);
            assertEquals(mime, asset.getContentType());
        }
    }

    @Test
    void assetFallbackRequiresNonEmptyStringContentTypeTest() {
        String[] rejected = {
                "{\"stringId\":1,\"contentType\":\"\",\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":\"   \",\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":null,\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":5,\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":true,\"url\":\"u\"}",
        };
        for (String json : rejected) {
            Exception e = assertThrows(Exception.class, () -> parse(json), json);
            assertTrue(rootMessage(e).startsWith("Wrong type: "), json + " -> " + rootMessage(e));
        }
        for (String json : new String[]{"{\"stringId\":1,\"url\":\"u\"}", "{\"stringId\":1,\"contentType\":{\"x\":1},\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":[\"image/png\"],\"url\":\"u\"}"}) {
            Exception e = assertThrows(Exception.class, () -> parse(json), json);
            assertEquals("Couldn't get class", rootMessage(e), json);
        }
    }

    @Test
    void crowdinTextTypesAreNeverAssetsTest() {
        for (String type : new String[]{"application/vnd.crowdin.text+new", "application/vnd.crowdin.text+markdown",
                "application/vnd.crowdin.anything"}) {
            String json = "{\"stringId\":1,\"contentType\":\"" + type + "\",\"url\":\"u\"}";
            Exception e = assertThrows(Exception.class, () -> parse(json), json);
            assertTrue(rootMessage(e).startsWith("Wrong type: "), json + " -> " + rootMessage(e));
        }
    }

    @Test
    void knownTypesAreUnchangedTest() {
        assertInstanceOf(PlainLanguageTranslations.class, parse("{\"stringId\":1,\"contentType\":\"text/plain\",\"url\":\"u\"}"));
        assertInstanceOf(PluralLanguageTranslations.class, parse("{\"stringId\":1,\"contentType\":\"application/vnd.crowdin.text+plural\",\"url\":\"u\"}"));
        assertInstanceOf(ICULanguageTranslations.class, parse("{\"stringId\":1,\"contentType\":\"application/vnd.crowdin.text+icu\",\"url\":\"u\"}"));
    }

    @Test
    void unknownTypesWithTextOrPluralsAreRejectedTest() {
        String[] rejected = {
                "{\"stringId\":1,\"contentType\":\"text/html\",\"text\":\"<b>x</b>\",\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":\"text/markdown\",\"text\":null,\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":\"image/png\",\"plurals\":[],\"url\":\"u\"}",
                "{\"stringId\":1,\"contentType\":\"application/x-new\",\"plurals\":[]}",
        };
        for (String json : rejected) {
            Exception e = assertThrows(Exception.class, () -> parse(json), json);
            assertTrue(rootMessage(e).startsWith("Wrong type: "), json + " -> " + rootMessage(e));
        }
    }

    /**
     * Remaining ambiguity: the documented asset shape cannot distinguish an unknown {@code text/*} type that has a
     * {@code url} but no {@code text}. {@code text/*} is not excluded because the API does not state that assets can
     * never use a {@code text/*} MIME type.
     */
    @Test
    void textMarkdownWithUrlAndNoTextIsReadAsAssetTest() {
        AssetLanguageTranslations asset = assertInstanceOf(AssetLanguageTranslations.class,
                parse("{\"stringId\":1,\"contentType\":\"text/markdown\",\"url\":\"u\"}"));
        assertEquals("text/markdown", asset.getContentType());
    }

    private LanguageTranslations parse(String translation) {
        return jsonTransformer.parse("{\"data\":" + translation + "}", LanguageTranslationsResponseObject.class).getData();
    }

    private static String rootMessage(Throwable e) {
        Throwable root = e;
        while (root.getCause() != null && root.getCause() != root) {
            root = root.getCause();
        }
        return root.getMessage();
    }
}
