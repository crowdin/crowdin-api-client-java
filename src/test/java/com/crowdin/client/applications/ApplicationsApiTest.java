package com.crowdin.client.applications;

import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationsApiTest extends TestClient {

    private final String applicationIdentifier = "identifier";
    private final String path = "path";
    private final String builtUrl = this.url + "/applications/" + applicationIdentifier + "/api/" + path;
    private final String builtUrlInstallation = this.url + "/applications/installations";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(builtUrl, HttpGet.METHOD_NAME, "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpPut.METHOD_NAME, "api/applications/updateOrRestoreApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpPost.METHOD_NAME, "api/applications/addApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpDelete.METHOD_NAME),
                RequestMock.build(builtUrl, HttpPatch.METHOD_NAME, "api/applications/editApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier, HttpGet.METHOD_NAME, "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier, HttpDelete.METHOD_NAME),
                RequestMock.build(builtUrlInstallation + "/" + applicationIdentifier, HttpPatch.METHOD_NAME, "api/applications/editApplicationInstallation.json", "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation, HttpGet.METHOD_NAME, "api/applications/applicationInstallation.json"),
                RequestMock.build(builtUrlInstallation, HttpPost.METHOD_NAME, "api/applications/installApplication.json", "api/applications/applicationInstallation.json")
        );
    }

    @Test
    void getApplicationData() {
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().getApplicationData(applicationIdentifier, path);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void updateOrRestoreApplicationData() {
        Map<String, Object> applicationDataPayload = new HashMap<>();
        applicationDataPayload.put("updateKey", "value");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().updateOrRestoreApplicationData(applicationIdentifier, path, applicationDataPayload);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void addApplicationData() {
        Map<String, Object> addApplicationDataPayload = new HashMap<>();
        addApplicationDataPayload.put("Key1", "value");
        addApplicationDataPayload.put("Key2", "value");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().addApplicationData(applicationIdentifier, path, addApplicationDataPayload);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void deleteApplicationData() {
        this.getApplicationsApi().deleteApplicationData(applicationIdentifier, path);
    }

    @Test
    void editApplicationData() {
        Map<String, Object> request = new HashMap<>();
        request.put("editKey", "editedValue");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().editApplicationData(applicationIdentifier, path, request);
        assertNotNull(response);
        assertEquals(response.getData().get("customKey1"), "value");
        assertEquals(response.getData().get("customKey2"), "value");
    }

    @Test
    void getApplicationInstallation() {
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().getApplicationInstallation(applicationIdentifier);
        assertNotNull(response);
        assertEquals(response.getData().get("identifier"), "example-application");
        assertEquals(response.getData().get("name"), "Application name");
    }

    @Test
    void listApplicationInstallations() {
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().listApplicationInstallations();
        assertNotNull(response);
        assertEquals(response.getData().get("identifier"), "example-application");
        assertEquals(response.getData().get("name"), "Application name");
    }

    @Test
    void installApplication() {
        Map<String, Object> request = new HashMap<>();
        Map<String, Object> user = new HashMap<>();
        user.put("value", "restricted");
        user.put("ids", new int[]{1});
        Map<String, Object> project = new HashMap<>();
        project.put("value", "restricted");
        project.put("ids", new int[]{1});
        Map<String, Object> permissions = new HashMap<>();
        permissions.put("user", user);
        permissions.put("project", project);
        request.put("url", "https://localhost.dev/crowdin.json");
        request.put("permissions", permissions);

        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().installApplication(request);
        assertNotNull(response);
        assertEquals(response.getData().get("identifier"), "example-application");
        assertEquals(response.getData().get("name"), "Application name");
    }

    @Test
    void deleteApplicationInstallation() {
        this.getApplicationsApi().deleteApplicationInstallation(applicationIdentifier);
    }

    @Test
    void editApplicationInstallation() {
        Map<String, Object> request = new HashMap<>();
        request.put("op", "replace");
        request.put("path", "/permissions");
        ResponseObject<Map<String, Object>> response = this.getApplicationsApi().editApplicationInstallation(applicationIdentifier, request);
        assertNotNull(response);
        assertEquals(response.getData().get("identifier"), "example-application");
        assertEquals(response.getData().get("name"), "Application name");
    }
}
