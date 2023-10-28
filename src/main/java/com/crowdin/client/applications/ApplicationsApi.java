package com.crowdin.client.applications;

import com.crowdin.client.applications.model.*;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseObject;


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
    public ResponseObject<ApplicationData> getApplicationData(String applicationIdentifier, String path) throws HttpException, HttpBadRequestException {
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
    public ResponseObject<ApplicationData> updateOrRestoreApplicationData(String applicationIdentifier, String path, UpdateOrRestoreApplicationDataRequest request) throws HttpException, HttpBadRequestException {
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
    public ResponseObject<ApplicationData> addApplicationData(String applicationIdentifier, String path, AddApplicationDataRequest request) throws HttpException, HttpBadRequestException {
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
    public ResponseObject<ApplicationData> editApplicationData(String applicationIdentifier, String path, EditApplicationDataRequest request) throws HttpException, HttpBadRequestException {
        String builtUrl = String.format("%s/applications/%s/api/%s", this.url, applicationIdentifier, path);
        ApplicationDataResponseObject response = this.httpClient.patch(builtUrl, request, new HttpRequestConfig(), ApplicationDataResponseObject.class);
        return ResponseObject.of(response.getData());
    }
}
