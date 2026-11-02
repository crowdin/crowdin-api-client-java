package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.projectsgroups.model.FileFormatSettingsResource;
import com.crowdin.client.projectsgroups.model.FmHtmlFileFormatSettings;
import com.crowdin.client.projectsgroups.model.HtmlFileFormatSettings;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code excludedElements} of HTML / front matter HTML file format settings is documented as an array of CSS
 * selectors, while SDK 1.36.0 modelled it as a boolean. Both representations must be readable.
 */
@SuppressWarnings("deprecation")
class ExcludedElementsSerializationTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void htmlSettingsResponseRepresentationsTest() {
        HtmlFileFormatSettings array = html("[\"div.x\",\"#footer\"]");
        assertEquals(Arrays.asList("div.x", "#footer"), array.getExcludedElementList());
        assertNull(array.getExcludedElements());

        HtmlFileFormatSettings bool = html("true");
        assertEquals(Boolean.TRUE, bool.getExcludedElements());
        assertNull(bool.getExcludedElementList());
        assertEquals(Boolean.FALSE, html("false").getExcludedElements());

        HtmlFileFormatSettings empty = html("[]");
        assertTrue(empty.getExcludedElementList().isEmpty());

        assertBothNull(html("null"));
        assertBothNull(html(null));
        assertBothNull(html("\"div.x\""));
        assertBothNull(html("{\"selector\":\"div\"}"));
    }

    @Test
    void fmHtmlSettingsResponseRepresentationsTest() {
        FmHtmlFileFormatSettings array = fmHtml("[\"div.x\"]");
        assertEquals(Collections.singletonList("div.x"), array.getExcludedElementList());
        assertNull(array.getExcludedElements());

        FmHtmlFileFormatSettings bool = fmHtml("true");
        assertEquals(Boolean.TRUE, bool.getExcludedElements());
        assertNull(bool.getExcludedElementList());

        FmHtmlFileFormatSettings missing = fmHtml(null);
        assertNull(missing.getExcludedElements());
        assertNull(missing.getExcludedElementList());
        assertEquals(Collections.singletonList("title"), missing.getExcludedFrontMatterElements());
    }

    @Test
    void responsesThroughFileFormatSettingsResourceTest() {
        for (String format : new String[]{"html", "fmHtml"}) {
            FileFormatSettingsResource bool = resource(format, "true");
            FileFormatSettingsResource array = resource(format, "[\"div.x\"]");
            FileFormatSettingsResource missing = resource(format, null);
            if ("html".equals(format)) {
                assertEquals(Boolean.TRUE, ((HtmlFileFormatSettings) bool.getSettings()).getExcludedElements());
                assertEquals(Collections.singletonList("div.x"), ((HtmlFileFormatSettings) array.getSettings()).getExcludedElementList());
                assertNull(((HtmlFileFormatSettings) missing.getSettings()).getExcludedElementList());
                assertEquals(Boolean.TRUE, ((HtmlFileFormatSettings) missing.getSettings()).getContentSegmentation());
            } else {
                assertEquals(Boolean.TRUE, ((FmHtmlFileFormatSettings) bool.getSettings()).getExcludedElements());
                assertEquals(Collections.singletonList("div.x"), ((FmHtmlFileFormatSettings) array.getSettings()).getExcludedElementList());
                assertNull(((FmHtmlFileFormatSettings) missing.getSettings()).getExcludedElements());
            }
        }
    }

    @Test
    void requestSerializationTest() {
        HtmlFileFormatSettings settings = new HtmlFileFormatSettings();
        assertEquals("{}", jsonTransformer.convert(settings));

        settings.setExcludedElements(true);
        assertEquals("{\"excludedElements\":true}", jsonTransformer.convert(settings));

        settings.setExcludedElementList(Arrays.asList("div.x", "#footer"));
        assertEquals("{\"excludedElements\":[\"div.x\",\"#footer\"]}", jsonTransformer.convert(settings));

        FmHtmlFileFormatSettings fmHtml = new FmHtmlFileFormatSettings();
        fmHtml.setExcludedElementList(Collections.singletonList("div.x"));
        assertEquals("{\"excludedElements\":[\"div.x\"]}", jsonTransformer.convert(fmHtml));
        fmHtml.setExcludedElementList(null);
        fmHtml.setExcludedElements(false);
        assertEquals("{\"excludedElements\":false}", jsonTransformer.convert(fmHtml));
    }

    @Test
    void roundTripTest() {
        HtmlFileFormatSettings settings = new HtmlFileFormatSettings();
        settings.setExcludedElementList(Arrays.asList("div.x", "#footer"));
        HtmlFileFormatSettings parsed = jsonTransformer.parse(jsonTransformer.convert(settings), HtmlFileFormatSettings.class);
        assertEquals(settings, parsed);

        HtmlFileFormatSettings legacy = new HtmlFileFormatSettings();
        legacy.setExcludedElements(true);
        assertEquals(legacy, jsonTransformer.parse(jsonTransformer.convert(legacy), HtmlFileFormatSettings.class));
    }

    private HtmlFileFormatSettings html(String excludedElements) {
        return jsonTransformer.parse(settingsJson(excludedElements, "\"contentSegmentation\":true"), HtmlFileFormatSettings.class);
    }

    private FmHtmlFileFormatSettings fmHtml(String excludedElements) {
        return jsonTransformer.parse(settingsJson(excludedElements, "\"excludedFrontMatterElements\":[\"title\"]"), FmHtmlFileFormatSettings.class);
    }

    private FileFormatSettingsResource resource(String format, String excludedElements) {
        String json = "{\"id\":1,\"name\":\"HTML\",\"format\":\"" + format + "\",\"extensions\":[\"html\"],\"settings\":"
                + settingsJson(excludedElements, "\"contentSegmentation\":true") + ",\"createdAt\":\"2023-09-19T15:10:43+00:00\"}";
        return jsonTransformer.parse(json, FileFormatSettingsResource.class);
    }

    private static String settingsJson(String excludedElements, String other) {
        return excludedElements == null ? "{" + other + "}" : "{\"excludedElements\":" + excludedElements + "," + other + "}";
    }

    private static void assertBothNull(HtmlFileFormatSettings settings) {
        assertNull(settings.getExcludedElements());
        assertNull(settings.getExcludedElementList());
        assertEquals(Boolean.TRUE, settings.getContentSegmentation());
    }
}
