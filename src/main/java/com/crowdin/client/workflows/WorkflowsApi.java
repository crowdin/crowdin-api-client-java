package com.crowdin.client.workflows;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.workflows.model.WorkflowStep;
import com.crowdin.client.workflows.model.WorkflowStepResponseList;
import com.crowdin.client.workflows.model.WorkflowStepResponseObject;
import com.crowdin.client.workflows.model.WorkflowTemplate;
import com.crowdin.client.workflows.model.WorkflowTemplateResponseList;
import com.crowdin.client.workflows.model.WorkflowTemplateResponseObject;

import java.util.Map;
import java.util.Optional;

public class WorkflowsApi extends CrowdinApi {
    public WorkflowsApi(Credentials credentials) {
        super(credentials);
    }

    public WorkflowsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of workflow steps
     */
    public ResponseList<WorkflowStep> listWorkflowSteps(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        WorkflowStepResponseList workflowStepResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/workflow-steps", new HttpConfig(queryParams), WorkflowStepResponseList.class);
        return WorkflowStepResponseList.to(workflowStepResponseList);
    }

    /**
     * @param projectId project identifier
     * @param stepId    workflow step identifier
     * @return workflow step
     */
    public ResponseObject<WorkflowStep> getWorkflowStep(Long projectId, Long stepId) throws HttpException, HttpBadRequestException {
        WorkflowStepResponseObject workflowStepResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/workflow-steps/" + stepId, new HttpConfig(), WorkflowStepResponseObject.class);
        return ResponseObject.of(workflowStepResponseObject.getData());
    }

    /**
     * @param groupId group identifier
     * @param limit   maximum number of items to retrieve (default 25)
     * @param offset  starting offset in the collection (default 0)
     * @return list of workflow templates
     */
    public ResponseList<WorkflowTemplate> listWorkflowTemplates(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        WorkflowTemplateResponseList workflowTemplateResponseList = this.httpClient.get(this.url + "/workflow-templates", new HttpConfig(queryParams), WorkflowTemplateResponseList.class);
        return WorkflowTemplateResponseList.to(workflowTemplateResponseList);
    }

    /**
     * @param templateId workflow template identifier
     * @return workflow step
     */
    public ResponseObject<WorkflowTemplate> getWorkflowTemplate(Long templateId) throws HttpException, HttpBadRequestException {
        WorkflowTemplateResponseObject workflowTemplateResponseObject = this.httpClient.get(this.url + "/workflow-templates/" + templateId, new HttpConfig(), WorkflowTemplateResponseObject.class);
        return ResponseObject.of(workflowTemplateResponseObject.getData());
    }
}
