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
     */
    public ResponseList<Bundle> listBundles(Long projectId) throws HttpException, HttpBadRequestException {
        BundleResponseList response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles", new HttpRequestConfig(), BundleResponseList.class);
        return BundleResponseList.to(response);
    }

    /**
     * @param projectId project identifier
     * @param request request object
     * @return newly created bundle
     */
    public ResponseObject<Bundle> addBundle(Long projectId, Bundle request) throws HttpException, HttpBadRequestException {
        BundleResponseObject response = this.httpClient.post(this.url + "/projects/" + projectId + "/bundles", request, new HttpRequestConfig(), BundleResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @return bundle object
     */
    public ResponseObject<Bundle> getBundle(Long projectId, Long bundleId) throws HttpException, HttpBadRequestException {
        BundleResponseObject response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles/" + bundleId, new HttpRequestConfig(), BundleResponseObject.class);
        return ResponseObject.of(response.getData());
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     */
    public void deleteBundle(Long projectId, Long bundleId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/projects/" + projectId + "/bundles/" + bundleId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param projectId project identifier
     * @param bundleId bundle identifier
     * @param request request object
     * @return updated bundle
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
     */
    public ResponseList<? extends FileInfo> listBundleFiles(Long projectId, Long bundleId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FileInfoResponseList response = this.httpClient.get(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/files", new HttpRequestConfig(queryParams), FileInfoResponseList.class);
        return FileInfoResponseList.to(response);
    }
}
