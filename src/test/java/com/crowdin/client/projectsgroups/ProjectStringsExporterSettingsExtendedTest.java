package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectStringsExporterSettingsExtendedTest extends TestClient {

    private final Long projectId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(formUrl(3L), HttpPatch.METHOD_NAME,
                        "api/projectsgroups/stringsexportersettings/editSettingsPatchRequest.json",
                        "api/projectsgroups/stringsexportersettings/singleAndroidFull.json"),
                RequestMock.build(formUrl(4L), HttpGet.METHOD_NAME, "api/projectsgroups/stringsexportersettings/singleMacosx.json"),
                RequestMock.build(formUrl(5L), HttpGet.METHOD_NAME, "api/projectsgroups/stringsexportersettings/singleXliff.json"),
                RequestMock.build(formUrl(6L), HttpGet.METHOD_NAME, "api/projectsgroups/stringsexportersettings/singleUnknownFormat.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/strings-exporter-settings", HttpPost.METHOD_NAME,
                        "api/projectsgroups/stringsexportersettings/addXliffSettingsRequest.json",
                        "api/projectsgroups/stringsexportersettings/singleXliff.json")
        );
    }

    private String formUrl(Long settingsId) {
        return this.url + "/projects/" + projectId + "/strings-exporter-settings/" + settingsId;
    }

    @Test
    public void editProjectStringsExporterSettingsWithPatchTest() {
        Map<String, Object> settings = new LinkedHashMap<>();
        settings.put("convertPlaceholders", true);
        settings.put("convertLineBreaks", true);
        settings.put("useCdataForStringsWithTags", false);

        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/settings");
        request.setValue(settings);

        ResponseObject<StringsExporterSettingsResource> response =
                this.getProjectsGroupsApi().editProjectStringsExporterSettingsWithPatch(projectId, 3L, singletonList(request));
        AndroidStringsExporterSettings android = (AndroidStringsExporterSettings) response.getData().getSettings();
        assertTrue(android.getConvertPlaceholders());
        assertTrue(android.getConvertLineBreaks());
        assertFalse(android.getUseCdataForStringsWithTags());
    }

    @Test
    public void getMacosxStringsExporterSettingsTest() {
        StringsExporterSettingsResource resource = this.getProjectsGroupsApi().getProjectStringsExporterSettings(projectId, 4L).getData();
        MacOSXStringsExporterSettings settings = (MacOSXStringsExporterSettings) resource.getSettings();
        assertFalse(settings.getConvertPlaceholders());
        assertTrue(settings.getConvertLineBreaks());
        assertTrue(settings.getExportContext());
    }

    @Test
    public void getXliffStringsExporterSettingsTest() {
        StringsExporterSettingsResource resource = this.getProjectsGroupsApi().getProjectStringsExporterSettings(projectId, 5L).getData();
        XliffStringsExporterSettings settings = (XliffStringsExporterSettings) resource.getSettings();
        assertEquals("es", settings.getLanguagePairMapping().get("uk"));
        assertFalse(settings.getCopySourceToEmptyTarget());
        assertTrue(settings.getExportTranslatorsComment());
    }

    @Test
    public void getUnknownFormatStringsExporterSettingsTest() {
        StringsExporterSettingsResource resource = this.getProjectsGroupsApi().getProjectStringsExporterSettings(projectId, 6L).getData();
        assertEquals(6L, resource.getId());
        assertEquals("some_new_format", resource.getFormat());
        assertNotNull(resource.getSettings());
        assertEquals(StringsExporterSettings.class, resource.getSettings().getClass());
    }

    @Test
    public void addXliffStringsExporterSettingsTest() {
        XliffStringsExporterSettings settings = new XliffStringsExporterSettings();
        settings.setCopySourceToEmptyTarget(false);
        settings.setExportTranslatorsComment(true);
        XliffStringsExporterSettingsRequest request = new XliffStringsExporterSettingsRequest();
        request.setSettings(settings);

        StringsExporterSettingsResource resource = this.getProjectsGroupsApi().addProjectStringsExporterSettings(projectId, request).getData();
        assertEquals(5L, resource.getId());
    }
}
