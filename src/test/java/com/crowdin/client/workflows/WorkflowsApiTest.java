package com.crowdin.client.workflows;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.sourcestrings.model.SourceString;
import com.crowdin.client.workflows.model.WorkflowStep;
import com.crowdin.client.workflows.model.WorkflowTemplate;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkflowsApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long workflowId = 313L;
    private final String workflowTitle = "Translation by Vendor";
    private final Long workflowTemplateId = 2L;
    private final String workflowTemplateTitle = "In-house + Machine Translation";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/workflow-steps", HttpGet.METHOD_NAME, "api/workflows/listWorkflows.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/workflow-steps/" + workflowId, HttpGet.METHOD_NAME, "api/workflows/workflow.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/workflow-steps/" + workflowId + "/strings", HttpGet.METHOD_NAME, "api/workflows/workflowStrings.json"),
                RequestMock.build(this.url + "/workflow-templates", HttpGet.METHOD_NAME, "api/workflows/listWorkflowTemplates.json"),
                RequestMock.build(this.url + "/workflow-templates/" + workflowTemplateId, HttpGet.METHOD_NAME, "api/workflows/workflowTemplate.json")
        );
    }

    @Test
    public void listWorkflowStepsTest() {
        ResponseList<WorkflowStep> workflowStepResponseList = this.getWorkflowsApi().listWorkflowSteps(projectId);
        assertEquals(workflowStepResponseList.getData().size(), 2);
    }

    @Test
    public void getWorkflowStepTest() {
        ResponseObject<WorkflowStep> workflowStep = this.getWorkflowsApi().getWorkflowStep(projectId, workflowId);
        assertEquals(workflowStep.getData().getId(), workflowId);
        assertEquals(workflowStep.getData().getTitle(), workflowTitle);
    }

    @Test
    public void listWorkflowStepStringsTest() {
        ResponseList<SourceString> workflowStepStrings = this.getWorkflowsApi().listWorkflowStepStrings(projectId, workflowId, null, null, null, null, null);
        assertEquals(1, workflowStepStrings.getData().size());
        assertEquals(projectId, workflowStepStrings.getData().get(0).getData().getProjectId());
    }

    @Test
    public void listWorkflowTemplatesTest() {
        ResponseList<WorkflowTemplate> workflowTemplateResponseList = this.getWorkflowsApi().listWorkflowTemplates(null, null, null);
        assertEquals(workflowTemplateResponseList.getData().size(), 1);
    }

    @Test
    public void getWorkflowTemplateTest() {
        ResponseObject<WorkflowTemplate> workflowTemplate = this.getWorkflowsApi().getWorkflowTemplate(workflowTemplateId);
        assertEquals(workflowTemplate.getData().getId(), workflowTemplateId);
        assertEquals(workflowTemplate.getData().getTitle(), workflowTemplateTitle);
    }
}
