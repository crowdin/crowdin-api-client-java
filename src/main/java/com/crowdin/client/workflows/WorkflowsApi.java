package com.crowdin.client.workflows;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.sourcestrings.model.SourceString;
import com.crowdin.client.sourcestrings.model.SourceStringResponseList;
import com.crowdin.client.workflows.model.WorkflowStep;
import com.crowdin.client.workflows.model.WorkflowStepResponseList;
import com.crowdin.client.workflows.model.WorkflowStepResponseObject;
import com.crowdin.client.workflows.model.WorkflowTemplate;
import com.crowdin.client.workflows.model.WorkflowTemplateResponseList;
import com.crowdin.client.workflows.model.WorkflowTemplateResponseObject;

import java.util.List;
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
     * @return list of workflow steps
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.workflow-steps.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<WorkflowStep> listWorkflowSteps(Long projectId) throws HttpException, HttpBadRequestException {
        WorkflowStepResponseList workflowStepResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/workflow-steps", new HttpRequestConfig(), WorkflowStepResponseList.class);
        return WorkflowStepResponseList.to(workflowStepResponseList);
    }

    /**
     * @param projectId project identifier
     * @param stepId workflow step identifier
     * @return workflow step
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.workflow-steps.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<WorkflowStep> getWorkflowStep(Long projectId, Long stepId) throws HttpException, HttpBadRequestException {
        WorkflowStepResponseObject workflowStepResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/workflow-steps/" + stepId, new HttpRequestConfig(), WorkflowStepResponseObject.class);
        return ResponseObject.of(workflowStepResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param stepId workflow step identifier
     * @param languageIds filter progress by language identifiers
     * @param orderBy ["id", "text", "identifier", "context", "createdAt", "updatedAt"]. List of OrderByField (default "id asc")
     * @param status ["todo", "done", "pending", "incomplete", "need_review"]. String status on the workflow step
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of project strings
     * @see <ul>
     * <li><a href="https://support.crowdin.com/developer/enterprise/api/v2/#tag/Workflows/operation/api.projects.workflow-steps.strings.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SourceString> listWorkflowStepStrings(Long projectId, Long stepId, String languageIds, List<OrderByField> orderBy, String status, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "languageIds", Optional.ofNullable(languageIds),
                "orderBy", Optional.ofNullable(OrderByField.generateSortParam(orderBy)),
                "status", Optional.ofNullable(status),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        SourceStringResponseList workflowStepStringsList = this.httpClient.get(this.url + "/projects/" + projectId + "/workflow-steps/" + stepId + "/strings", new HttpRequestConfig(queryParams), SourceStringResponseList.class);
        return SourceStringResponseList.to(workflowStepStringsList);
    }

    /**
     * @param groupId group identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of workflow templates
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.workflow-templates.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<WorkflowTemplate> listWorkflowTemplates(Long groupId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "groupId", Optional.ofNullable(groupId),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        WorkflowTemplateResponseList workflowTemplateResponseList = this.httpClient.get(this.url + "/workflow-templates", new HttpRequestConfig(queryParams), WorkflowTemplateResponseList.class);
        return WorkflowTemplateResponseList.to(workflowTemplateResponseList);
    }

    /**
     * @param templateId workflow template identifier
     * @return workflow step
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.workflow-templates.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<WorkflowTemplate> getWorkflowTemplate(Long templateId) throws HttpException, HttpBadRequestException {
        WorkflowTemplateResponseObject workflowTemplateResponseObject = this.httpClient.get(this.url + "/workflow-templates/" + templateId, new HttpRequestConfig(), WorkflowTemplateResponseObject.class);
        return ResponseObject.of(workflowTemplateResponseObject.getData());
    }
}
