package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.*;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.AddProjectFileFormatSettingsRequest;
import com.crowdin.client.projectsgroups.model.DocxFileFormatSettings;
import com.crowdin.client.projectsgroups.model.FileFormatSettingsResource;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectFileFormatSettingsApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long fileFormatSettingsId = 1L;
    private final String link = "test.com";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                // LIST
                RequestMock.build(
                        formUrl_fileFormatSettings(projectId),
                        HttpGet.METHOD_NAME,
                        "api/projectsgroups/fileformatsettings/listProjectFileFormatSettingsResponse.json"
                ),

                // ADD
                RequestMock.build(
                        formUrl_fileFormatSettings(projectId),
                        HttpPost.METHOD_NAME,
                        "api/projectsgroups/fileformatsettings/addProjectFileFormatSettingsRequest.json",
                        "api/projectsgroups/fileformatsettings/commonResponses_single.json"
                ),

                // GET
                RequestMock.build(
                        formUrl_fileFormatSettingsId(projectId, fileFormatSettingsId),
                        HttpGet.METHOD_NAME,
                        "api/projectsgroups/fileformatsettings/commonResponses_single.json"
                ),

                // PATCH
                RequestMock.build(
                        formUrl_fileFormatSettingsId(projectId, fileFormatSettingsId),
                        HttpPatch.METHOD_NAME,
                        "api/projectsgroups/fileformatsettings/editProjectFileFormatSettingsRequest.json",
                        "api/projectsgroups/fileformatsettings/commonResponses_single.json"
                ),

                // DELETE
                RequestMock.build(
                        formUrl_fileFormatSettingsId(projectId, fileFormatSettingsId),
                        HttpDelete.METHOD_NAME
                ),

                // RESET
                RequestMock.build(
                        formUrl_fileFormatSettingsId_customSegmentations(projectId, fileFormatSettingsId),
                        HttpDelete.METHOD_NAME
                ),

                // DOWNLOAD
                RequestMock.build(
                        formUrl_fileFormatSettingsId_customSegmentations(projectId, fileFormatSettingsId),
                        HttpGet.METHOD_NAME,
                        "api/projectsgroups/fileformatsettings/downloadProjectFileFormatSettingsResponse.json"
                )
        );
    }

    //<editor-fold desc="Form Url methods for mocks">
    private String formUrl_fileFormatSettings(Long projectId) {
        return this.url + "/projects/" + projectId + "/file-format-settings";
    }

    private String formUrl_fileFormatSettingsId(Long projectId, Long fileFormatSettingsId) {
        return this.url + "/projects/" + projectId + "/file-format-settings/" + fileFormatSettingsId;
    }

    private String formUrl_fileFormatSettingsId_customSegmentations(Long projectId, Long fileFormatSettingsId) {
        return this.url + "/projects/" + projectId + "/file-format-settings/" + fileFormatSettingsId + "/custom-segmentations";
    }
    //</editor-fold>

    @Test
    public void downloadProjectFileFormatSettingsCustomSegmentation() {
        ResponseObject<DownloadLink> response = this.getProjectsGroupsApi().downloadProjectFileFormatSettingsCustomSegmentation(projectId, fileFormatSettingsId);
        assertEquals(link, response.getData().getUrl());
    }

    @Test
    public void resetProjectFileFormatSettingsCustomSegmentation() {
        this.getProjectsGroupsApi().resetProjectFileFormatSettingsCustomSegmentation(projectId, fileFormatSettingsId);
    }

    @Test
    public void listProjectFileFormatSettings() {
        ResponseList<FileFormatSettingsResource> response = this.getProjectsGroupsApi().listProjectFileFormatSettings(projectId);
        assertFileFormatSettings(response.getData().get(0).getData());
    }

    @Test
    public void addProjectFileFormatSettings() {
        AddProjectFileFormatSettingsRequest request = new AddProjectFileFormatSettingsRequest() {{
           setFormat("docx");
           setSettings(new DocxFileFormatSettings() {{
               setCleanTagsAggressively(true);
               setSrxStorageId(1L);
           }});
        }};

        ResponseObject<FileFormatSettingsResource> response = this.getProjectsGroupsApi().addProjectFileFormatSettings(projectId, request);
        assertFileFormatSettings(response.getData());
    }

    @Test
    public void getProjectFileFormatSettings() {
        ResponseObject<FileFormatSettingsResource> response = this.getProjectsGroupsApi().getProjectFileFormatSettings(projectId, fileFormatSettingsId);
        assertFileFormatSettings(response.getData());
    }

    @Test
    public void deleteProjectFileFormatSettings() {
        this.getProjectsGroupsApi().deleteProjectFileFormatSettings(projectId, fileFormatSettingsId);
    }

    @Test
    public void editProjectFileFormatSettings() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/format");
                setValue("docx");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/settings");
                setValue(new DocxFileFormatSettings() {{
                    setCleanTagsAggressively(true);
                    setSrxStorageId(1L);
                }});
            }});
        }};

        ResponseObject<FileFormatSettingsResource> response =
                this.getProjectsGroupsApi().editProjectFileFormatSettings(projectId, fileFormatSettingsId, request);

        assertFileFormatSettings(response.getData());
    }

    @SneakyThrows
    private static void assertFileFormatSettings(FileFormatSettingsResource resource) {
        assertNotNull(resource);

        assertEquals(44, resource.getId());
        assertEquals("DOCX", resource.getName());
        assertEquals("docx", resource.getFormat());
        assertEquals(Arrays.asList("docx", "odt", "xlsx", "pptx", "ods", "odg", "odp", "mif"), resource.getExtensions());

        DocxFileFormatSettings docxSettings = new DocxFileFormatSettings() {{
            setCleanTagsAggressively(true);
            setSrxStorageId(1L);
        }};
        assertEquals(docxSettings, resource.getSettings());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        assertEquals(sdf.parse("2019-09-19T15:10:43+00:00"), resource.getCreatedAt());
        assertEquals(sdf.parse("2019-09-19T15:10:46+00:00"), resource.getUpdatedAt());
    }
}
