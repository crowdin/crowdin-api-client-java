package com.crowdin.client.placeholders;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.placeholders.model.AddOrganizationPlaceholderRequest;
import com.crowdin.client.placeholders.model.AddProjectPlaceholderRequest;
import com.crowdin.client.placeholders.model.OrganizationPlaceholder;
import com.crowdin.client.placeholders.model.ProjectPlaceholder;
import com.crowdin.client.placeholders.model.ProjectPlaceholderType;
import com.crowdin.client.placeholders.model.SystemPlaceholder;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaceholdersApiTest extends TestClient {

    private final Long projectId = 8L;
    private final Long customPlaceholderId = 1L;
    private final Long projectPlaceholderId = 2L;
    private final String definition = "start, then \"http\", maybe \"s\", then \"://\", end";
    private final String description = "URL validation";
    private final String argumentDelimiter = "\"";
    private final List<String> formats = Arrays.asList("docbook", "adoc");

    @Override
    public List<RequestMock> getMocks() {
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("limit", 25);
        pageParams.put("offset", 0);

        String projectUrl = this.url + "/projects/" + projectId;
        return Arrays.asList(
                RequestMock.build(projectUrl + "/system-placeholders", HttpGet.METHOD_NAME, "api/placeholders/listSystemPlaceholders.json", pageParams),
                RequestMock.build(projectUrl + "/system-placeholders", HttpPatch.METHOD_NAME, "api/placeholders/editSystemPlaceholdersRequest.json", "api/placeholders/editSystemPlaceholders.json"),
                RequestMock.build(this.url + "/custom-placeholders", HttpGet.METHOD_NAME, "api/placeholders/listCustomPlaceholders.json"),
                RequestMock.build(this.url + "/custom-placeholders", HttpPost.METHOD_NAME, "api/placeholders/addCustomPlaceholderRequest.json", "api/placeholders/customPlaceholder.json"),
                RequestMock.build(this.url + "/custom-placeholders/" + customPlaceholderId, HttpGet.METHOD_NAME, "api/placeholders/customPlaceholder.json"),
                RequestMock.build(this.url + "/custom-placeholders/" + customPlaceholderId, HttpPatch.METHOD_NAME, "api/placeholders/editCustomPlaceholderRequest.json", "api/placeholders/customPlaceholder.json"),
                RequestMock.build(this.url + "/custom-placeholders/" + customPlaceholderId, HttpDelete.METHOD_NAME),
                RequestMock.build(projectUrl + "/placeholders", HttpGet.METHOD_NAME, "api/placeholders/listProjectPlaceholders.json", pageParams),
                RequestMock.build(projectUrl + "/placeholders", HttpPost.METHOD_NAME, "api/placeholders/addProjectPlaceholderRequest.json", "api/placeholders/projectPlaceholder.json"),
                RequestMock.build(projectUrl + "/placeholders/" + projectPlaceholderId, HttpGet.METHOD_NAME, "api/placeholders/projectPlaceholder.json"),
                RequestMock.build(projectUrl + "/placeholders/" + projectPlaceholderId, HttpPatch.METHOD_NAME, "api/placeholders/editProjectPlaceholderRequest.json", "api/placeholders/projectPlaceholder.json"),
                RequestMock.build(projectUrl + "/placeholders/" + projectPlaceholderId, HttpDelete.METHOD_NAME)
        );
    }

    @Test
    public void listSystemPlaceholdersTest() {
        ResponseList<SystemPlaceholder> response = this.getPlaceholdersApi().listSystemPlaceholders(projectId, 25, 0);
        assertEquals(1, response.getData().size());
        SystemPlaceholder placeholder = response.getData().get(0).getData();
        assertEquals("printfSpecifier", placeholder.getId());
        assertTrue(placeholder.getIsEnabled());
        assertEquals("C-style format specifiers", placeholder.getLabel());
        assertEquals(singletonList("%s"), placeholder.getExamples());
        assertEquals("C, PHP, Java, Objective-C, Android, and Qt", placeholder.getDescription());
        assertEquals(25, response.getPagination().getLimit());
    }

    @Test
    public void editSystemPlaceholdersTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/printfSpecifier/isEnabled");
        request.setValue(false);

        ResponseList<SystemPlaceholder> response = this.getPlaceholdersApi().editSystemPlaceholders(projectId, singletonList(request));
        assertEquals(1, response.getData().size());
        assertEquals("printfSpecifier", response.getData().get(0).getData().getId());
        assertFalse(response.getData().get(0).getData().getIsEnabled());
        assertNull(response.getPagination());
    }

    @Test
    public void listCustomPlaceholdersTest() {
        ResponseList<OrganizationPlaceholder> response = this.getPlaceholdersApi().listCustomPlaceholders(null, null);
        assertEquals(1, response.getData().size());
        assertCustomPlaceholder(response.getData().get(0).getData());
    }

    @Test
    public void addCustomPlaceholderTest() {
        AddOrganizationPlaceholderRequest request = new AddOrganizationPlaceholderRequest();
        request.setDefinition(definition);
        request.setDescription(description);
        request.setArgumentDelimiter(argumentDelimiter);
        ResponseObject<OrganizationPlaceholder> response = this.getPlaceholdersApi().addCustomPlaceholder(request);
        assertCustomPlaceholder(response.getData());
    }

    @Test
    public void getCustomPlaceholderTest() {
        ResponseObject<OrganizationPlaceholder> response = this.getPlaceholdersApi().getCustomPlaceholder(customPlaceholderId);
        assertCustomPlaceholder(response.getData());
    }

    @Test
    public void editCustomPlaceholderTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/description");
        request.setValue(description);
        ResponseObject<OrganizationPlaceholder> response = this.getPlaceholdersApi().editCustomPlaceholder(customPlaceholderId, singletonList(request));
        assertCustomPlaceholder(response.getData());
    }

    @Test
    public void deleteCustomPlaceholderTest() {
        this.getPlaceholdersApi().deleteCustomPlaceholder(customPlaceholderId);
    }

    @Test
    public void listProjectPlaceholdersTest() {
        ResponseList<ProjectPlaceholder> response = this.getPlaceholdersApi().listProjectPlaceholders(projectId, 25, 0);
        assertEquals(1, response.getData().size());
        assertProjectPlaceholder(response.getData().get(0).getData());
    }

    @Test
    public void addProjectPlaceholderTest() {
        AddProjectPlaceholderRequest request = new AddProjectPlaceholderRequest();
        request.setCustomPlaceholderId(customPlaceholderId);
        request.setType(ProjectPlaceholderType.HIGH);
        request.setIndex(1);
        request.setIsBlocking(false);
        request.setFormats(formats);
        ResponseObject<ProjectPlaceholder> response = this.getPlaceholdersApi().addProjectPlaceholder(projectId, request);
        assertProjectPlaceholder(response.getData());
    }

    @Test
    public void getProjectPlaceholderTest() {
        ResponseObject<ProjectPlaceholder> response = this.getPlaceholdersApi().getProjectPlaceholder(projectId, projectPlaceholderId);
        assertProjectPlaceholder(response.getData());
    }

    @Test
    public void editProjectPlaceholderTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/isBlocking");
        request.setValue(true);
        ResponseObject<ProjectPlaceholder> response = this.getPlaceholdersApi().editProjectPlaceholder(projectId, projectPlaceholderId, singletonList(request));
        assertProjectPlaceholder(response.getData());
    }

    @Test
    public void deleteProjectPlaceholderTest() {
        this.getPlaceholdersApi().deleteProjectPlaceholder(projectId, projectPlaceholderId);
    }

    private void assertCustomPlaceholder(OrganizationPlaceholder placeholder) {
        assertEquals(customPlaceholderId, placeholder.getId());
        assertEquals(definition, placeholder.getDefinition());
        assertEquals(description, placeholder.getDescription());
        assertEquals(argumentDelimiter, placeholder.getArgumentDelimiter());
    }

    private void assertProjectPlaceholder(ProjectPlaceholder placeholder) {
        assertEquals(projectPlaceholderId, placeholder.getId());
        assertEquals(customPlaceholderId, placeholder.getCustomPlaceholderId());
        assertEquals(ProjectPlaceholderType.HIGH, placeholder.getType());
        assertEquals(1, placeholder.getIndex());
        assertFalse(placeholder.getIsBlocking());
        assertEquals(formats, placeholder.getFormats());
    }
}
