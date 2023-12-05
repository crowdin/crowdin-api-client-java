package com.crowdin.client.applications;

import com.crowdin.client.applications.installations.model.*;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
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
                RequestMock.build(builtUrlInstallation, HttpGet.METHOD_NAME, "api/applications/installApplicationList.json"),
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
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().getApplicationInstallation(applicationIdentifier);
        assertNotNull(response);
        assertEquals(response.getData().getIdentifier(), "example-application");
        assertEquals(response.getData().getName(), "Application name");
    }

    @Test
    void listApplicationInstallations() {
        ResponseList<ApplicationInstallation> responseList = this.getApplicationsApi().listApplicationInstallations(null, null);
        assertNotNull(responseList);
        assertEquals(1, responseList.getData().size());
    }

    @Test
    void installApplication() {
        InstallApplicationRequestObject request = new InstallApplicationRequestObject();
        request.setUrl("https://localhost.dev/crowdin.json");
        User user = new User();
        user.setValue(UserPermissions.RESTRICTED);
        user.setIds(new int[]{1});
        Project project = new Project();
        project.setValue(ProjectPermissions.RESTRICTED);
        project.setIds(new int[]{1});
        Permissions permissions = new Permissions();
        permissions.setUser(user);
        permissions.setProject(project);
        request.setPermissions(permissions);
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().installApplication(request);
        assertNotNull(response);
        assertEquals(response.getData().getIdentifier(), "example-application");
        assertEquals(response.getData().getName(), "Application name");
    }

    @Test
    void deleteApplicationInstallation() {
        this.getApplicationsApi().deleteApplicationInstallation(applicationIdentifier);
    }

    @Test
    void editApplicationInstallation() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/permissions");
        request.setValue("example-application");
        ResponseObject<ApplicationInstallation> response = this.getApplicationsApi().editApplicationInstallation(applicationIdentifier, singletonList(request));
        assertNotNull(response);
        assertEquals(response.getData().getIdentifier(), "example-application");
        assertEquals(response.getData().getName(), "Application name");
    }
}
