package com.crowdin.client.applications;

import com.crowdin.client.applications.installations.model.ApplicationInstallation;
import com.crowdin.client.applications.installations.model.ApplicationInstallationResponseList;
import com.crowdin.client.applications.installations.model.ApplicationInstallationResponseObject;
import com.crowdin.client.applications.installations.model.InstallApplicationRequestObject;
import com.crowdin.client.applications.model.*;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ApplicationsApi extends CrowdinApi {
    public ApplicationsApi(Credentials credentials) {
        super(credentials);
    }

    public ApplicationsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> getApplicationData(String applicationIdentifier, String path) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> updateOrRestoreApplicationData(String applicationIdentifier, String path, Map<String, Object> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.put(builtUrl, request, new HttpRequestConfig(), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> addApplicationData(String applicationIdentifier, String path, Map<String, Object> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.members.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.members.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationData(String applicationIdentifier, String path) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param applicationIdentifier identifier of the application
     * @param path The path is implemented by the application
     * @param request request object
     * @return application data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.api.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.api.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Map<String, Object>> editApplicationData(String applicationIdentifier, String path, Map<String, Object> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param identifier identifier of the application installation
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> getApplicationInstallation(String identifier) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s", this.url, identifier);
        ApplicationInstallationResponseObject response = this.httpClient.get(builtUrl, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param limit A maximum number of items to retrieve
     * @param offset A starting offset in the collection
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<ApplicationInstallation> listApplicationInstallations(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations", this.url);
        Map<String, Optional<Integer>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ApplicationInstallationResponseList response = this.httpClient.get(builtUrl, new HttpRequestConfig(queryParams), ApplicationInstallationResponseList.class);
        return ApplicationInstallationResponseList.to(response);
    }

    /**
     * @param request request object
     * @return application installation data
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> installApplication(InstallApplicationRequestObject request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations", this.url);
        ApplicationInstallationResponseObject response = this.httpClient.post(builtUrl, request, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param identifier identifier of the application installation
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteApplicationInstallation(String identifier) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s", this.url, identifier);
        this.httpClient.delete(builtUrl, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param identifier identifier of the application installation
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.applications.installations.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.applications.installations.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<ApplicationInstallation> editApplicationInstallation(String identifier, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/installations/%s", this.url, identifier);
        ApplicationInstallationResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), ApplicationInstallationResponseObject.class);
        return ResponseObject.of(response.getData());
    }
}
