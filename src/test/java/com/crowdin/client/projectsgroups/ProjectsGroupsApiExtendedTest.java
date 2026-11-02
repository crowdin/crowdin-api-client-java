package com.crowdin.client.projectsgroups;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.projectsgroups.model.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectsGroupsApiExtendedTest extends TestClient {

    private final Long groupId = 1L;
    private final Long projectId = 8L;
    private final Long projectSettingsId = 11L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/groups", HttpGet.METHOD_NAME, "api/projectsgroups/listGroups.json", new HashMap<String, Object>() {{
                    put("filter", "KB");
                    put("limit", 10);
                }}),
                RequestMock.build(this.url + "/groups", HttpPost.METHOD_NAME, "api/projectsgroups/addGroupWithTemplateRequest.json", "api/projectsgroups/groupDetails.json"),
                RequestMock.build(this.url + "/groups/" + groupId, HttpGet.METHOD_NAME, "api/projectsgroups/groupDetails.json"),
                RequestMock.build(this.url + "/projects", HttpGet.METHOD_NAME, "api/projectsgroups/listProjects.json", new HashMap<String, Object>() {{
                    put("filter", "Knowledge");
                    put("userId", 6L);
                }}),
                RequestMock.build(this.url + "/projects", HttpPost.METHOD_NAME, "api/projectsgroups/addProjectCrowdinRequest.json", "api/projectsgroups/project.json"),
                RequestMock.build(this.url + "/projects", HttpPost.METHOD_NAME, "api/projectsgroups/addEnterpriseProjectRequest.json", "api/projectsgroups/projectSettingsEnterprise.json"),
                RequestMock.build(this.url + "/projects/" + projectSettingsId, HttpGet.METHOD_NAME, "api/projectsgroups/projectSettingsEnterprise.json")
        );
    }

    @Test
    public void listGroupsWithFilterTest() {
        ListGroupOptions options = new ListGroupOptions();
        options.setFilter("KB");
        options.setLimit(10);
        ResponseList<Group> response = this.getProjectsGroupsApi().listGroups(options);
        assertEquals(1, response.getData().size());
        assertEquals(groupId, response.getData().get(0).getData().getId());
    }

    @Test
    public void addGroupWithTemplateTest() {
        AddGroupRequest request = new AddGroupRequest();
        request.setName("KB materials");
        request.setParentId(2L);
        request.setSavingsReportSettingsTemplateId(3L);
        Group group = this.getProjectsGroupsApi().addGroup(request).getData();
        assertGroup(group);
    }

    @Test
    public void getGroupDetailsTest() {
        assertGroup(this.getProjectsGroupsApi().getGroup(groupId).getData());
    }

    private void assertGroup(Group group) {
        assertEquals(groupId, group.getId());
        assertEquals("https://example.crowdin.com/u/groups/1", group.getWebUrl());
        assertEquals(3L, group.getSavingsReportSettingsTemplateId());
    }

    @Test
    public void listProjectsWithFilterAndUserIdTest() {
        ListProjectOptions options = new ListProjectOptions();
        options.setFilter("Knowledge");
        options.setUserId(6L);
        ResponseList<? extends Project> response = this.getProjectsGroupsApi().listProjects(options);
        assertEquals(1, response.getData().size());
        assertEquals(projectId, response.getData().get(0).getData().getId());
    }

    @Test
    public void addProjectSendsTagsDetectionTest() {
        ProjectRequest request = new ProjectRequest();
        request.setName("Knowledge Base");
        request.setSourceLanguageId("en");
        // legacy setter must be sent as "tagsDetection"
        request.setTagDetection(1);
        assertEquals(1, request.getTagsDetection());
        assertEquals(1, request.getTagDetection());
        request.setQaCheckCategories(new QaCheckCategories() {{
            setNumbers(true);
            setAi(true);
            setOutdated(false);
            setMdx(true);
            setUnifiedPlaceholders(false);
        }});
        request.setQaCheckActionAiPromptId(8L);
        request.setContextReviewAiPromptId(9L);
        request.setAssignedStyleGuides(Arrays.asList(4L, 5L));

        ResponseObject<? extends Project> response = this.getProjectsGroupsApi().addProject(request);
        assertEquals(projectId, response.getData().getId());
    }

    @Test
    public void addEnterpriseProjectTest() {
        WorkflowTemplateStepConfig.WorkflowTemplateStepConfigTranslateProofread translateStep = new WorkflowTemplateStepConfig.WorkflowTemplateStepConfigTranslateProofread();
        translateStep.setId(1);
        translateStep.setLanguages(singletonList("uk"));
        WorkflowTemplateStepConfig.TranslateProofreadConfig translateConfig = new WorkflowTemplateStepConfig.TranslateProofreadConfig();
        translateConfig.setAssignees(Collections.singletonMap("uk", Arrays.asList(20L, 21L)));
        translateConfig.setAssignedTeams(Collections.singletonMap("uk", singletonList(5L)));
        translateStep.setConfig(translateConfig);

        WorkflowTemplateStepConfig.WorkflowTemplateStepConfigTMPreTranslate tmStep = new WorkflowTemplateStepConfig.WorkflowTemplateStepConfigTMPreTranslate();
        tmStep.setId(2);
        tmStep.setLanguages(singletonList("uk"));
        WorkflowTemplateStepConfig.Config tmConfig = new WorkflowTemplateStepConfig.Config();
        tmConfig.setMinRelevant(60);
        tmConfig.setAutoSubstitution(true);
        tmConfig.setAutoApproveOption("perfectMatchOnly");
        tmStep.setConfig(tmConfig);

        WorkflowTemplateStepConfig.WorkflowTemplateStepConfigAIPreTranslate aiStep = new WorkflowTemplateStepConfig.WorkflowTemplateStepConfigAIPreTranslate();
        aiStep.setId(3);
        aiStep.setLanguages(singletonList("uk"));
        aiStep.setPromptId(11L);

        EnterpriseProjectRequest request = new EnterpriseProjectRequest();
        request.setName("Knowledge Base");
        request.setSourceLanguageId("en");
        request.setTemplateId(4L);
        request.setSteps(Arrays.asList(translateStep, tmStep, aiStep));
        // legacy Integer setter must be sent as a boolean
        request.setExportStringsThatPassedWorkflow(1);
        assertTrue(request.getExportStringsThatPassedWorkflowEnabled());
        assertEquals(1, request.getExportStringsThatPassedWorkflow());
        // legacy Boolean field is never sent
        request.setInContextPseudoLanguageId(true);
        request.setInContextPseudoLanguageIdValue("uk");
        request.setExternalQaCheckIds(singletonList(2L));
        request.setExternalQaChecksIgnorable(Collections.singletonMap("2", false));
        Map<String, Object> fields = new HashMap<>();
        fields.put("some-field-1", "some value 1");
        request.setFields(fields);
        request.setQaCheckActionAiPromptId(8L);
        request.setContextReviewAiPromptId(9L);
        request.setAssignedStyleGuides(singletonList(4L));

        ResponseObject<? extends Project> response = this.getProjectsGroupsApi().addProject(request);
        assertTrue(response.getData() instanceof ProjectSettings);
        assertEquals(projectSettingsId, response.getData().getId());
    }

    @Test
    public void getEnterpriseProjectSettingsTest() {
        ResponseObject<? extends Project> response = this.getProjectsGroupsApi().getProject(projectSettingsId);
        assertTrue(response.getData() instanceof ProjectSettings);
        ProjectSettings settings = (ProjectSettings) response.getData();
        assertEquals(projectSettingsId, settings.getId());
        assertEquals("bg", settings.getBackground());
        assertEquals("some value 1", settings.getFields().get("some-field-1"));
        assertEquals(true, settings.getFields().get("some-field-3"));
        assertEquals(2, settings.getTagsDetection());
        assertEquals("readOnly", settings.getGlossaryAccessOption());
        assertTrue(settings.getQaCheckCategories().getNumbers());
        assertFalse(settings.getQaCheckCategories().getAi());
        assertTrue(settings.getQaCheckCategories().getOutdated());
        assertFalse(settings.getQaCheckCategories().getMdx());
        assertTrue(settings.getQaCheckCategories().getUnifiedPlaceholders());
        assertFalse(settings.getQaChecksIgnorableCategories().getNumbers());
        assertEquals(singletonList(2L), settings.getExternalQaCheckIds());
        assertEquals(Boolean.FALSE, settings.getExternalQaChecksIgnorable().get("2"));
        assertEquals(Arrays.asList(4L, 5L), settings.getAssignedStyleGuides());
        assertEquals(7L, settings.getEditorSuggestionAiPromptId());
        assertEquals(8L, settings.getQaCheckActionAiPromptId());
        assertEquals(9L, settings.getContextReviewAiPromptId());
        assertEquals(10L, settings.getAlignmentActionAiPromptId());
        assertTrue(settings.getExportStringsThatPassedWorkflow());
        assertTrue(settings.isHasCrowdsourcing());
        assertTrue(settings.isExternal());
    }
}
