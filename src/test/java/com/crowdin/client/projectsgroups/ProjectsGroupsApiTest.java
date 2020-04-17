package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.AddGroupRequest;
import com.crowdin.client.projectsgroups.model.AddProjectRequest;
import com.crowdin.client.projectsgroups.model.Group;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectsGroupsApiTest extends TestClient {

    private final Long groupId = 1L;
    private final Long groupParentId = 2L;
    private final String groupName = "KB materials";
    private final Long projectId = 8L;
    private final Long projectSettingsId = 9L;
    private final String projectName = "Knowledge Base";
    private final String sourceLanguageId = "en";
    private final String targetLanguageId = "uk";
    private final Long templateId = 4L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/groups", HttpGet.METHOD_NAME, "api/projectsgroups/listGroups.json"),
                RequestMock.build(this.url + "/groups", HttpPost.METHOD_NAME, "api/projectsgroups/addGroupRequest.json", "api/projectsgroups/group.json"),
                RequestMock.build(this.url + "/groups/" + groupId, HttpGet.METHOD_NAME, "api/projectsgroups/group.json"),
                RequestMock.build(this.url + "/groups/" + groupId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/groups/" + groupId, HttpPatch.METHOD_NAME, "api/projectsgroups/editGroup.json", "api/projectsgroups/group.json"),
                RequestMock.build(this.url + "/projects", HttpGet.METHOD_NAME, "api/projectsgroups/listProjects.json"),
                RequestMock.build(this.url + "/projects", HttpPost.METHOD_NAME, "api/projectsgroups/addProjectRequest.json", "api/projectsgroups/project.json"),
                RequestMock.build(this.url + "/projects/" + projectId, HttpGet.METHOD_NAME, "api/projectsgroups/project.json"),
                RequestMock.build(this.url + "/projects/" + projectSettingsId, HttpGet.METHOD_NAME, "api/projectsgroups/projectSettings.json"),
                RequestMock.build(this.url + "/projects/" + projectId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId, HttpPatch.METHOD_NAME, "api/projectsgroups/editProject.json", "api/projectsgroups/project.json")
        );
    }

    @Test
    public void listGroupsTest() {
        ResponseList<Group> groupResponseList = this.getProjectsGroupsApi().listGroups(null, null, null);
        assertEquals(groupResponseList.getData().size(), 1);
        assertEquals(groupResponseList.getData().get(0).getData().getId(), groupId);
        assertEquals(groupResponseList.getData().get(0).getData().getName(), groupName);
    }

    @Test
    public void addGroupTest() {
        AddGroupRequest request = new AddGroupRequest();
        request.setName(groupName);
        request.setParentId(groupParentId);
        ResponseObject<Group> groupResponseObject = this.getProjectsGroupsApi().addGroup(request);
        assertEquals(groupResponseObject.getData().getId(), groupId);
        assertEquals(groupResponseObject.getData().getName(), groupName);
    }

    @Test
    public void getGroupTest() {
        ResponseObject<Group> groupResponseObject = this.getProjectsGroupsApi().getGroup(groupId);
        assertEquals(groupResponseObject.getData().getId(), groupId);
        assertEquals(groupResponseObject.getData().getName(), groupName);
    }

    @Test
    public void deleteGroupTest() {
        this.getProjectsGroupsApi().deleteGroup(groupId);
    }

    @Test
    public void editGroupTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(groupName);
        request.setPath("/name");
        ResponseObject<Group> groupResponseObject = this.getProjectsGroupsApi().editGroup(groupId, singletonList(request));
        assertEquals(groupResponseObject.getData().getId(), groupId);
        assertEquals(groupResponseObject.getData().getName(), groupName);
    }


    @Test
    public void listProjectsTest() {
        ResponseList<? extends Project> projectResponseList = this.getProjectsGroupsApi().listProjects(null, null, null, null);
        assertEquals(projectResponseList.getData().size(), 1);
        assertEquals(projectResponseList.getData().get(0).getData().getId(), projectId);
        assertEquals(projectResponseList.getData().get(0).getData().getName(), projectName);
    }

    @Test
    public void addProjectTest() {
        AddProjectRequest request = new AddProjectRequest();
        request.setName(projectName);
        request.setSourceLanguageId(sourceLanguageId);
        request.setTemplateId(templateId);
        request.setGroupId(groupId);
        request.setTargetLanguageIds(singletonList(targetLanguageId));
        ResponseObject<? extends Project> projectResponseObject = this.getProjectsGroupsApi().addProject(request);
        assertEquals(projectResponseObject.getData().getId(), projectId);
        assertEquals(projectResponseObject.getData().getName(), projectName);
    }

    @Test
    public void getProjectTest() {
        ResponseObject<? extends Project> projectResponseObject = this.getProjectsGroupsApi().getProject(projectId);
        assertEquals(projectResponseObject.getData().getId(), projectId);
        assertEquals(projectResponseObject.getData().getName(), projectName);
    }

    @Test
    public void getProjectSettingsTest() {
        ResponseObject<? extends Project> projectResponseObject = this.getProjectsGroupsApi().getProject(projectSettingsId);
        assertTrue(projectResponseObject.getData() instanceof ProjectSettings);
        ProjectSettings projectSettings = (ProjectSettings) projectResponseObject.getData();
        assertEquals(projectSettings.getId(), projectSettingsId);
        assertEquals(projectSettings.getName(), projectName);
        assertTrue(projectSettings.getLanguageMapping() instanceof Map);
        assertTrue(((Map) projectSettings.getLanguageMapping()).containsKey(targetLanguageId));
    }

    @Test
    public void deleteProjectTest() {
        this.getProjectsGroupsApi().deleteProject(projectId);
    }

    @Test
    public void editProjectTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(projectName);
        request.setPath("/name");
        ResponseObject<? extends Project> projectResponseObject = this.getProjectsGroupsApi().editProject(projectId, singletonList(request));
        assertEquals(projectResponseObject.getData().getId(), projectId);
        assertEquals(projectResponseObject.getData().getName(), projectName);
    }
}
