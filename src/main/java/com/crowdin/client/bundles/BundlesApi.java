package com.crowdin.client.bundles;

import com.crowdin.client.bundles.model.*;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
import com.crowdin.client.sourcefiles.model.FileInfo;
import com.crowdin.client.sourcefiles.model.FileInfoResponseList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BundlesApi extends CrowdinApi {


    public BundlesApi(Credentials credentials) {
        super(credentials);
    }

    public BundlesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param projectId project identifier
     * @return list of bundles
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Bundle> listBundles(Long projectId) throws HttpException, HttpBadRequestException {
        BundleResponseList response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles", new HttpRequestConfig(), BundleResponseList.class);
        return BundleResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created bundle
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Bundle> addBundle(Long projectId, Bundle request) throws HttpException, HttpBadRequestException {
        BundleResponseObject response = this.httpClient.post(this.url + "/projects/" + projectId + "/bundles", request, new HttpRequestConfig(), BundleResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @return bundle object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Bundle> getBundle(Long projectId, Long bundleId) throws HttpException, HttpBadRequestException {
        BundleResponseObject response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles/" + bundleId, new HttpRequestConfig(), BundleResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteBundle(Long projectId, Long bundleId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/bundles/" + bundleId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @param request request object
     * @return updated bundle
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Bundle> editBundle(Long projectId, Long bundleId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        BundleResponseObject response = this.httpClient.patch(this.url + "/projects/" + projectId + "/bundles/" + bundleId, request, new HttpRequestConfig(), BundleResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of bundles file resource
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.files.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.files.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<? extends FileInfo> listBundleFiles(Long projectId, Long bundleId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileInfoResponseList response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/files", new HttpRequestConfig(queryParams), FileInfoResponseList.class);
        return FileInfoResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @param exportId export identifier, consists of 36 characters
     * @return download link to bundle
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.exports.download.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.exports.download.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<DownloadLink> downloadBundle(Long projectId, Long bundleId, String exportId) throws HttpException, HttpBadRequestException {
        DownloadLinkResponseObject response = this.httpClient.get(
                this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/exports/" + exportId + "/download",
                new HttpRequestConfig(),
                DownloadLinkResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @return freshly created bundle export object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.exports.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.exports.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<BundleExport> exportBundle(Long projectId, Long bundleId, Bundle request) throws HttpException, HttpBadRequestException {
        BundleExportResponseObject response = this.httpClient.post(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/exports",
                request,
                new HttpRequestConfig(),
                BundleExportResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @param exportId export identifier, consists of 36 characters
     * @return requested bundle export object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.projects.bundles.exports.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.projects.bundles.exports.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    ResponseObject<BundleExport> checkBundleExportStatus(Long projectId, Long bundleId, String exportId) {
        BundleExportResponseObject response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/exports/" + exportId,
                new HttpRequestConfig(),
                BundleExportResponseObject.class);
        return ResponseObject.of(response.getData());
    }
}
