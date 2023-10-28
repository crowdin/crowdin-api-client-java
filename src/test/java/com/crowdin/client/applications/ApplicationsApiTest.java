package com.crowdin.client.applications;

import com.crowdin.client.applications.model.*;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.*;
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

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(builtUrl, HttpGet.METHOD_NAME, "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpPut.METHOD_NAME, "api/applications/updateOrRestoreApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpPost.METHOD_NAME, "api/applications/addApplicationData.json", "api/applications/applicationData.json"),
                RequestMock.build(builtUrl, HttpDelete.METHOD_NAME),
                RequestMock.build(builtUrl, HttpPatch.METHOD_NAME, "api/applications/editApplicationData.json", "api/applications/applicationData.json")
        );
    }

    @Test
    void getApplicationData() {
        ResponseObject<ApplicationData> response = this.getApplicationsApi().getApplicationData(applicationIdentifier, path);
        assertNotNull(response);
    }

    @Test
    void updateOrRestoreApplicationData() {
        Map<String, Object> applicationDataPayload = new HashMap<>();
        applicationDataPayload.put("updateKey", "value");
        UpdateOrRestoreApplicationDataRequest updateOrRestoreApplicationDataRequest = new UpdateOrRestoreApplicationDataRequest();
        updateOrRestoreApplicationDataRequest.setData(applicationDataPayload);
        ResponseObject<ApplicationData> response = this.getApplicationsApi().updateOrRestoreApplicationData(applicationIdentifier, path, updateOrRestoreApplicationDataRequest);
        assertNotNull(response);
    }

    @Test
    void addApplicationData() {
        Map<String, Object> addApplicationDataPayload = new HashMap<>();
        addApplicationDataPayload.put("Key1", "value");
        addApplicationDataPayload.put("Key2", "value");
        AddApplicationDataRequest applicationDataRequest = new AddApplicationDataRequest();
        applicationDataRequest.setData(addApplicationDataPayload);
        ResponseObject<ApplicationData> response = this.getApplicationsApi().addApplicationData(applicationIdentifier, path, applicationDataRequest);
        assertNotNull(response);
    }

    @Test
    void deleteApplicationData() {
        this.getApplicationsApi().deleteApplicationData(applicationIdentifier, path);
    }

    @Test
    void editApplicationData() {
        Map<String, Object> request = new HashMap<>();
        request.put("editKey", "editedValue");
        EditApplicationDataRequest editApplicationDataRequest = new EditApplicationDataRequest();
        editApplicationDataRequest.setData(request);
        ResponseObject<ApplicationData> response = this.getApplicationsApi().editApplicationData(applicationIdentifier, path, editApplicationDataRequest);
        assertNotNull(response);
    }

}