package com.crowdin.client.projectsgroups;

import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectFileFormatSettingsExtendedTest extends TestClient {

    private final Long projectId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(formUrl(2L), HttpGet.METHOD_NAME, "api/projectsgroups/fileformatsettings/htmlSettingsResponse.json"),
                RequestMock.build(formUrl(3L), HttpGet.METHOD_NAME, "api/projectsgroups/fileformatsettings/csvSettingsResponse.json"),
                RequestMock.build(formUrl(4L), HttpGet.METHOD_NAME, "api/projectsgroups/fileformatsettings/mdSettingsResponse.json"),
                RequestMock.build(formUrl(5L), HttpGet.METHOD_NAME, "api/projectsgroups/fileformatsettings/docxSettingsResponse.json"),
                RequestMock.build(formUrl(6L), HttpGet.METHOD_NAME, "api/projectsgroups/fileformatsettings/mdxV1SettingsResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/file-format-settings", HttpPost.METHOD_NAME,
                        "api/projectsgroups/fileformatsettings/addHtmlSettingsRequest.json",
                        "api/projectsgroups/fileformatsettings/htmlSettingsResponse.json")
        );
    }

    private String formUrl(Long settingsId) {
        return this.url + "/projects/" + projectId + "/file-format-settings/" + settingsId;
    }

    @Test
    public void getHtmlFileFormatSettingsTest() {
        FileFormatSettingsResource resource = this.getProjectsGroupsApi().getProjectFileFormatSettings(projectId, 2L).getData();
        assertHtmlSettings(resource);
    }

    private void assertHtmlSettings(FileFormatSettingsResource resource) {
        HtmlFileFormatSettings settings = (HtmlFileFormatSettings) resource.getSettings();
        assertEquals(Arrays.asList(".no-translate", "#footer"), settings.getExcludedElementList());
        assertNull(settings.getExcludedElements());
        assertEquals(Arrays.asList("b", "i"), settings.getInlineTags());
        assertTrue(settings.getHideAttributeValues());
        assertFalse(settings.getCustomSegmentation());
        assertTrue(settings.getContentSegmentation());
    }

    @Test
    public void addHtmlFileFormatSettingsTest() {
        HtmlFileFormatSettings settings = new HtmlFileFormatSettings();
        settings.setContentSegmentation(true);
        settings.setInlineTags(Arrays.asList("b", "i"));
        settings.setExcludedElementList(Arrays.asList(".no-translate", "#footer"));
        // legacy boolean field must not be sent
        settings.setExcludedElements(true);

        AddProjectFileFormatSettingsRequest request = new AddProjectFileFormatSettingsRequest();
        request.setFormat("html");
        request.setSettings(settings);

        FileFormatSettingsResource resource = this.getProjectsGroupsApi().addProjectFileFormatSettings(projectId, request).getData();
        assertHtmlSettings(resource);
    }

    @Test
    public void getCsvFileFormatSettingsTest() {
        FileFormatSettingsResource resource = this.getProjectsGroupsApi().getProjectFileFormatSettings(projectId, 3L).getData();
        assertTrue(resource.getSettings() instanceof CsvFileFormatSettings);
        // backward compatible with the previous OtherFileFormatSettings fallback
        assertTrue(resource.getSettings() instanceof OtherFileFormatSettings);
        CsvFileFormatSettings settings = (CsvFileFormatSettings) resource.getSettings();
        assertFalse(settings.getContentSegmentation());
        assertTrue(settings.getCustomSegmentation());
        assertEquals("/%locale%/%original_file_name%", settings.getExportPattern());
    }

    @Test
    public void getMdFileFormatSettingsTest() {
        FileFormatSettingsResource resource = this.getProjectsGroupsApi().getProjectFileFormatSettings(projectId, 4L).getData();
        MdFileFormatSettings settings = (MdFileFormatSettings) resource.getSettings();
        assertEquals(singletonList("kbd"), settings.getInlineTags());
        assertEquals("asterisk", settings.getStrongMarker());
        assertEquals("underscore", settings.getEmphasisMarker());
        assertEquals("dash", settings.getUnorderedListBullet());
        assertEquals("evenly_distribute_cells", settings.getTableColumnWidth());
        assertEquals("double", settings.getFrontMatterQuotes());
        assertFalse(settings.getCustomSegmentation());
    }

    @Test
    public void getDocxFileFormatSettingsTest() {
        FileFormatSettingsResource resource = this.getProjectsGroupsApi().getProjectFileFormatSettings(projectId, 5L).getData();
        DocxFileFormatSettings settings = (DocxFileFormatSettings) resource.getSettings();
        assertTrue(settings.getCleanTagsAggressively());
        assertFalse(settings.getCustomSegmentation());
        assertTrue(settings.getTranslateDocProperties());
        assertFalse(settings.getTranslateComments());
        assertEquals("\n", settings.getLineSeparatorReplacement());
        assertEquals(singletonList("HYPERLINK"), settings.getComplexFieldDefinitionsToExtract());
        assertEquals("#000000", settings.getWordFontColorsMinIgnoranceThreshold());
        assertEquals("#FFFFFF", settings.getWordFontColorsMaxIgnoranceThreshold());
        assertEquals(singletonList("Code"), settings.getExcludeWordStyles());
        assertEquals(singletonList("yellow"), settings.getWordHighlightColors());
        assertEquals(singletonList("red"), settings.getWordExcludedColors());
        assertEquals(singletonList("blue"), settings.getExcelExcludedColors());
        assertTrue(settings.getTranslateExcelExcludeColors());
        assertTrue(settings.getAllowWordStyleOptimization());
    }

    @Test
    public void getMdxV1FileFormatSettingsTest() {
        FileFormatSettingsResource resource = this.getProjectsGroupsApi().getProjectFileFormatSettings(projectId, 6L).getData();
        MdxV1FileFormatSettings settings = (MdxV1FileFormatSettings) resource.getSettings();
        assertEquals("mdx_v2", settings.getType());
        assertTrue(settings.getExcludeCodeBlocks());
        assertEquals("underscore", settings.getStrongMarker());
        assertTrue(settings.getCustomSegmentation());
    }
}
