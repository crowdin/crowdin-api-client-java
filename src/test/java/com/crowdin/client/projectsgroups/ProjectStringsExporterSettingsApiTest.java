package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.*;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectStringsExporterSettingsApiTest extends TestClient {
    private final Long projectId = 1L;
    private final Long settingsId = 2L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                // LIST
                RequestMock.build(
                        formUrl_many(projectId),
                        HttpGet.METHOD_NAME,
                        "api/projectsgroups/stringsexportersettings/list.json"
                ),

                // ADD
                RequestMock.build(
                        formUrl_many(projectId),
                        HttpPost.METHOD_NAME,
                        "api/projectsgroups/stringsexportersettings/addSettingsRequest.json",
                        "api/projectsgroups/stringsexportersettings/single.json"
                ),

                // GET
                RequestMock.build(
                        formUrl_single(projectId, settingsId),
                        HttpGet.METHOD_NAME,
                        "api/projectsgroups/stringsexportersettings/single.json"
                ),

                // DELETE
                RequestMock.build(
                        formUrl_single(projectId, settingsId),
                        HttpDelete.METHOD_NAME
                ),

                // EDIT
                RequestMock.build(
                        formUrl_single(projectId, settingsId),
                        HttpPatch.METHOD_NAME,
                        "api/projectsgroups/stringsexportersettings/editSettingsRequest.json",
                        "api/projectsgroups/stringsexportersettings/single.json"
                )
        );
    }

    //<editor-fold desc="Form Url methods for mocks">

    private String formUrl_many(Long projectId) {
        return this.url + "/projects/" + projectId + "/strings-exporter-settings";
    }

    private String formUrl_single(Long projectId, Long settingsId) {
        return this.url + "/projects/" + projectId + "/strings-exporter-settings/" + settingsId;
    }

    //</editor-fold>

    @Test
    public void listProjectStringsExporterSettings() {
        ResponseList<StringsExporterSettingsResource> response = this.getProjectsGroupsApi().listProjectStringsExporterSettings(projectId);
        assertStringsExporterSettingsResource(response.getData().get(0).getData());
    }

    @Test
    public void addProjectStringsExporterSettings() {
        StringsExporterSettingsRequest request = new XliffStringsExporterSettingsRequest() {{
            setSettings(new XliffStringsExporterSettings() {{
                setLanguagePairMapping(new HashMap<String, String>() {{
                    put("uk", "es");
                    put("de", "en");
                }});
            }});
        }};

        ResponseObject<StringsExporterSettingsResource> response = this.getProjectsGroupsApi().addProjectStringsExporterSettings(projectId, request);
        assertStringsExporterSettingsResource(response.getData());
    }

    @Test
    public void getProjectStringsExporterSettings() {
        ResponseObject<StringsExporterSettingsResource> response = this.getProjectsGroupsApi().getProjectStringsExporterSettings(projectId, settingsId);
        assertStringsExporterSettingsResource(response.getData());
    }

    @Test
    public void deleteProjectStringsExporterSettings() {
        this.getProjectsGroupsApi().deleteProjectStringsExporterSettings(projectId, settingsId);
    }

    @Test
    public void editProjectStringsExporterSettings() {
        StringsExporterSettingsRequest request = new AndroidStringsExporterSettingsRequest() {{
            setSettings(new AndroidStringsExporterSettings() {{
                setConvertPlaceholders(true);
            }});
        }};

        ResponseObject<StringsExporterSettingsResource> response =
                this.getProjectsGroupsApi().editProjectStringsExporterSettings(projectId, settingsId, request);
        assertStringsExporterSettingsResource(response.getData());
    }

    @SneakyThrows
    private static void assertStringsExporterSettingsResource(StringsExporterSettingsResource resource) {
        assertNotNull(resource);

        assertEquals(2, resource.getId());
        assertEquals("android", resource.getFormat());

        assertNotNull(resource.getSettings());
        AndroidStringsExporterSettings settings = (AndroidStringsExporterSettings) resource.getSettings();
        assertFalse(settings.getConvertPlaceholders());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        assertEquals(sdf.parse("2019-09-19T15:10:43+00:00"), resource.getCreatedAt());
        assertEquals(sdf.parse("2019-09-19T15:10:46+00:00"), resource.getUpdatedAt());
    }
}
