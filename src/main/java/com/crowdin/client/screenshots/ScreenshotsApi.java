package com.crowdin.client.screenshots;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.screenshots.model.AddScreenshotRequest;
import com.crowdin.client.screenshots.model.AddTagRequest;
import com.crowdin.client.screenshots.model.ReplaceTagsRequest;
import com.crowdin.client.screenshots.model.Screenshot;
import com.crowdin.client.screenshots.model.ScreenshotResponseList;
import com.crowdin.client.screenshots.model.ScreenshotResponseObject;
import com.crowdin.client.screenshots.model.Tag;
import com.crowdin.client.screenshots.model.TagResponseList;
import com.crowdin.client.screenshots.model.TagResponseObject;
import com.crowdin.client.screenshots.model.UpdateScreenshotRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScreenshotsApi extends CrowdinApi {
    public ScreenshotsApi(Credentials credentials) {
        super(credentials);
    }

    public ScreenshotsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of screenshots
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Screenshot> listScreenshots(Long projectId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ScreenshotResponseList screenshotResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/screenshots", new HttpRequestConfig(queryParams), ScreenshotResponseList.class);
        return ScreenshotResponseList.to(screenshotResponseList);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created screenshot
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Screenshot> addScreenshot(Long projectId, AddScreenshotRequest request) throws HttpException, HttpBadRequestException {
        ScreenshotResponseObject screenshotResponseObject = this.httpClient.post(this.url + "/projects/" + projectId + "/screenshots", request, new HttpRequestConfig(), ScreenshotResponseObject.class);
        return ResponseObject.of(screenshotResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @return screenshot
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Screenshot> getScreenshot(Long projectId, Long screenshotId) throws HttpException, HttpBadRequestException {
        ScreenshotResponseObject screenshotResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, new HttpRequestConfig(), ScreenshotResponseObject.class);
        return ResponseObject.of(screenshotResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param request request object
     * @return updated screenshot
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.put" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.put" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Screenshot> updateScreenshot(Long projectId, Long screenshotId, UpdateScreenshotRequest request) throws HttpException, HttpBadRequestException {
        ScreenshotResponseObject screenshotResponseObject = this.httpClient.put(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, request, new HttpRequestConfig(), ScreenshotResponseObject.class);
        return ResponseObject.of(screenshotResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteScreenshot(Long projectId, Long screenshotId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param request request object
     * @return updated screenshot
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Screenshot> editScreenshot(Long projectId, Long screenshotId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        ScreenshotResponseObject screenshotResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId, request, new HttpRequestConfig(), ScreenshotResponseObject.class);
        return ResponseObject.of(screenshotResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of tags
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Tag> listTags(Long projectId, Long screenshotId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        TagResponseList tagResponseList = this.httpClient.get(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", new HttpRequestConfig(queryParams), TagResponseList.class);
        return TagResponseList.to(tagResponseList);
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param request request object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.putMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.putMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void replaceTags(Long projectId, Long screenshotId, ReplaceTagsRequest request) throws HttpException, HttpBadRequestException {
        this.httpClient.put(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", request, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param request request object
     * @return newly created tags
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<List<Tag>> addTag(Long projectId, Long screenshotId, List<AddTagRequest> request) throws HttpException, HttpBadRequestException {
        TagResponseList tagResponseList = this.httpClient.post(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", request, new HttpRequestConfig(), TagResponseList.class);
        return ResponseObject.of(tagResponseList.getData().stream()
                .map(TagResponseObject::getData)
                .collect(Collectors.toList()));
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.deleteMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.deleteMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void clearTags(Long projectId, Long screenshotId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags", new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param tagId tag identifier
     * @return tag
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Tag> getTag(Long projectId, Long screenshotId, Long tagId) throws HttpException, HttpBadRequestException {
        TagResponseObject tagResponseObject = this.httpClient.get(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags/" + tagId, new HttpRequestConfig(), TagResponseObject.class);
        return ResponseObject.of(tagResponseObject.getData());
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param tagId tag identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteTag(Long projectId, Long screenshotId, Long tagId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags/" + tagId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param screenshotId screenshot identifier
     * @param tagId tag identifier
     * @param request request object
     * @return updated screenshot
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.screenshots.tags.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.screenshots.tags.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Tag> editTag(Long projectId, Long screenshotId, Long tagId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        TagResponseObject tagResponseObject = this.httpClient.patch(this.url + "/projects/" + projectId + "/screenshots/" + screenshotId + "/tags/" + tagId, request, new HttpRequestConfig(), TagResponseObject.class);
        return ResponseObject.of(tagResponseObject.getData());
    }
}
