package com.crowdin.client.placeholders;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.placeholders.model.AddOrganizationPlaceholderRequest;
import com.crowdin.client.placeholders.model.AddProjectPlaceholderRequest;
import com.crowdin.client.placeholders.model.OrganizationPlaceholder;
import com.crowdin.client.placeholders.model.OrganizationPlaceholderResponseList;
import com.crowdin.client.placeholders.model.OrganizationPlaceholderResponseObject;
import com.crowdin.client.placeholders.model.ProjectPlaceholder;
import com.crowdin.client.placeholders.model.ProjectPlaceholderResponseList;
import com.crowdin.client.placeholders.model.ProjectPlaceholderResponseObject;
import com.crowdin.client.placeholders.model.SystemPlaceholder;
import com.crowdin.client.placeholders.model.SystemPlaceholderResponseList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlaceholdersApi extends CrowdinApi {
    public PlaceholdersApi(Credentials credentials) {
        super(credentials);
    }

    public PlaceholdersApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of system placeholders with their state in the project
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.system-placeholders.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.system-placeholders.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SystemPlaceholder> listSystemPlaceholders(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        SystemPlaceholderResponseList systemPlaceholderResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/system-placeholders", new HttpRequestConfig(queryParams), SystemPlaceholderResponseList.class);
        return SystemPlaceholderResponseList.to(systemPlaceholderResponseList);
    }

    /**
     * Turns system placeholders on and off. Each operation path has the form {@code /{systemPlaceholderId}/isEnabled}.
     *
     * @param projectId project identifier
     * @param request request object
     * @return changed system placeholders (no pagination)
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.system-placeholders.batchPatch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.system-placeholders.batchPatch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SystemPlaceholder> editSystemPlaceholders(Long projectId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        SystemPlaceholderResponseList systemPlaceholderResponseList = this.httpClient.patch(this.url + "/projects/" + projectId + "/system-placeholders", request, new HttpRequestConfig(), SystemPlaceholderResponseList.class);
        return SystemPlaceholderResponseList.to(systemPlaceholderResponseList);
    }

    /**
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of custom placeholders of the organization
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-placeholders.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<OrganizationPlaceholder> listCustomPlaceholders(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        OrganizationPlaceholderResponseList organizationPlaceholderResponseList = this.httpClient.get(this.url + "/custom-placeholders", new HttpRequestConfig(queryParams), OrganizationPlaceholderResponseList.class);
        return OrganizationPlaceholderResponseList.to(organizationPlaceholderResponseList);
    }

    /**
     * @param request request object
     * @return newly created custom placeholder
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-placeholders.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<OrganizationPlaceholder> addCustomPlaceholder(AddOrganizationPlaceholderRequest request) throws HttpException, HttpBadRequestException {
        OrganizationPlaceholderResponseObject organizationPlaceholderResponseObject = this.httpClient.post(this.url + "/custom-placeholders", request, new HttpRequestConfig(), OrganizationPlaceholderResponseObject.class);
        return ResponseObject.of(organizationPlaceholderResponseObject.getData());
    }

    /**
     * @param customPlaceholderId custom placeholder identifier
     * @return custom placeholder
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-placeholders.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<OrganizationPlaceholder> getCustomPlaceholder(Long customPlaceholderId) throws HttpException, HttpBadRequestException {
        OrganizationPlaceholderResponseObject organizationPlaceholderResponseObject = this.httpClient.get(this.url + "/custom-placeholders/" + customPlaceholderId, new HttpRequestConfig(), OrganizationPlaceholderResponseObject.class);
        return ResponseObject.of(organizationPlaceholderResponseObject.getData());
    }

    /**
     * @param customPlaceholderId custom placeholder identifier
     * @param request request object
     * @return updated custom placeholder
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-placeholders.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<OrganizationPlaceholder> editCustomPlaceholder(Long customPlaceholderId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        OrganizationPlaceholderResponseObject organizationPlaceholderResponseObject = this.httpClient.patch(this.url + "/custom-placeholders/" + customPlaceholderId, request, new HttpRequestConfig(), OrganizationPlaceholderResponseObject.class);
        return ResponseObject.of(organizationPlaceholderResponseObject.getData());
    }

    /**
     * @param customPlaceholderId custom placeholder identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.custom-placeholders.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteCustomPlaceholder(Long customPlaceholderId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/custom-placeholders/" + customPlaceholderId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of custom placeholders assigned to the project
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.placeholders.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ProjectPlaceholder> listProjectPlaceholders(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ProjectPlaceholderResponseList projectPlaceholderResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/placeholders", new HttpRequestConfig(queryParams), ProjectPlaceholderResponseList.class);
        return ProjectPlaceholderResponseList.to(projectPlaceholderResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly assigned project placeholder
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.placeholders.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ProjectPlaceholder> addProjectPlaceholder(Long projectId, AddProjectPlaceholderRequest request) throws HttpException, HttpBadRequestException {
        ProjectPlaceholderResponseObject projectPlaceholderResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/placeholders", request, new HttpRequestConfig(), ProjectPlaceholderResponseObject.class);
        return ResponseObject.of(projectPlaceholderResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param projectPlaceholderId project placeholder identifier
     * @return project placeholder
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.placeholders.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ProjectPlaceholder> getProjectPlaceholder(Long projectId, Long projectPlaceholderId) throws HttpException, HttpBadRequestException {
        ProjectPlaceholderResponseObject projectPlaceholderResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/placeholders/" + projectPlaceholderId, new HttpRequestConfig(), ProjectPlaceholderResponseObject.class);
        return ResponseObject.of(projectPlaceholderResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param projectPlaceholderId project placeholder identifier
     * @param request request object
     * @return updated project placeholder
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.placeholders.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ProjectPlaceholder> editProjectPlaceholder(Long projectId, Long projectPlaceholderId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        ProjectPlaceholderResponseObject projectPlaceholderResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/placeholders/" + projectPlaceholderId, request, new HttpRequestConfig(), ProjectPlaceholderResponseObject.class);
        return ResponseObject.of(projectPlaceholderResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param projectPlaceholderId project placeholder identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.placeholders.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteProjectPlaceholder(Long projectId, Long projectPlaceholderId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/placeholders/" + projectPlaceholderId, new HttpRequestConfig(), Void.class);
    }
}
