package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.AddGroupRequest;
import com.crowdin.client.projectsgroups.model.ProjectRequest;
import com.crowdin.client.projectsgroups.model.Group;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import com.crowdin.client.projectsgroups.model.QaCheckCategories;
import com.crowdin.client.projectsgroups.model.TmPenalties;
import com.crowdin.client.projectsgroups.model.TmPriority;
import com.crowdin.client.projectsgroups.model.TmTimeElapsed;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectsGroupsApiTest extends TestClient {

    private final Long groupId = 1L;
    private final Long groupParentId = 2L;
    private final String groupName = "KB materials";
    private final Long projectId = 8L;
    private final Long projectSettingsId = 9L;
    private final Long projectSettingsId2 = 10L;
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
                RequestMock.build(this.url + "/projects/" + projectSettingsId2, HttpGet.METHOD_NAME, "api/projectsgroups/projectSettings_tmPenaltiesArray.json"),
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
        ProjectRequest request = new ProjectRequest();
        request.setType(0);
        request.setNormalizePlaceholder(true);
        request.setSaveMetaInfoInSource(true);
        request.setName("Knowledge Base");
        request.setIdentifier("1f198a4e907688bc65834a6d5a6000c3");
        request.setQaCheckIsActive(true);
        QaCheckCategories qaCheckCategories = new QaCheckCategories();
        qaCheckCategories.setEmpty(true);
        qaCheckCategories.setSize(true);
        qaCheckCategories.setTags(false);
        request.setQaCheckCategories(qaCheckCategories);

        request.setQaChecksIgnorableCategories(new QaCheckCategories() {{
            setPunctuation(true);
            setSymbolRegister(true);
            setSpecialSymbols(false);
            setWrongTranslation(false);
        }});
        request.setTmApprovedSuggestionsOnly(false);

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
        assertNotNull(((ProjectSettings) projectResponseObject.getData()).getNotificationSettings());
        assertEquals(((ProjectSettings) projectResponseObject.getData()).getNotificationSettings().getManagerNewStrings(), false);

        QaCheckCategories qaChecksIgnorableCategories = projectSettings.getQaChecksIgnorableCategories();
        assertTrue(qaChecksIgnorableCategories.getSize());
        assertTrue(qaChecksIgnorableCategories.getTags());
        assertTrue(qaChecksIgnorableCategories.getSpaces());
        assertTrue(qaChecksIgnorableCategories.getVariables());
        assertTrue(qaChecksIgnorableCategories.getPunctuation());
        assertTrue(qaChecksIgnorableCategories.getSymbolRegister());
        assertTrue(qaChecksIgnorableCategories.getSpecialSymbols());
        assertTrue(qaChecksIgnorableCategories.getWrongTranslation());
        assertTrue(qaChecksIgnorableCategories.getSpellcheck());
        assertTrue(qaChecksIgnorableCategories.getTerms());
        assertTrue(qaChecksIgnorableCategories.getAndroid());
        assertFalse(qaChecksIgnorableCategories.getEmpty());
        assertFalse(qaChecksIgnorableCategories.getIcu());
        assertFalse(qaChecksIgnorableCategories.getDuplicate());
        assertFalse(qaChecksIgnorableCategories.getFtl());

        TmPenalties tmPenalties = projectSettings.getTmPenalties();
        assertNotNull(tmPenalties);
        assertEquals(1, tmPenalties.getAutoSubstitution());
        assertEquals(1, tmPenalties.getMultipleTranslations());

        TmPriority tmPriority = tmPenalties.getTmPriority();
        assertNotNull(tmPriority);
        assertEquals(2, tmPriority.getPriority());
        assertEquals(1, tmPriority.getPenalty());

        TmTimeElapsed lastUsage = tmPenalties.getTimeSinceLastUsage();
        assertNotNull(lastUsage);
        assertEquals(2, lastUsage.getMonths());
        assertEquals(1, lastUsage.getPenalty());

        TmTimeElapsed lastModified = tmPenalties.getTimeSinceLastModified();
        assertNotNull(lastModified);
        assertEquals(2, lastModified.getMonths());
        assertEquals(1, lastModified.getPenalty());

        assertFalse(projectSettings.getTmApprovedSuggestionsOnly());
    }

    @Test
    public void getProjectSettingsTest_tmPenaltiesEmptyArray() {
        ResponseObject<? extends Project> response = this.getProjectsGroupsApi().getProject(projectSettingsId2);

        ProjectSettings settings = (ProjectSettings) response.getData();
        assertNull(settings.getTmPenalties());
    }

    @Test
    public void deleteProjectTest() {
        this.getProjectsGroupsApi().deleteProject(projectId);
    }

    @Test
    public void editProjectTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/name");
                setValue(projectName);
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/qaCheckCategories/ftl");
                setValue(false);
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/qaChecksIgnorableCategories/android");
                setValue(true);
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/tmPenalties/autoSubstitution");
                setValue(1);
            }});
        }};

        ResponseObject<? extends Project> projectResponseObject = this.getProjectsGroupsApi().editProject(projectId, request);
        assertEquals(projectResponseObject.getData().getId(), projectId);
        assertEquals(projectResponseObject.getData().getName(), projectName);
    }
}
