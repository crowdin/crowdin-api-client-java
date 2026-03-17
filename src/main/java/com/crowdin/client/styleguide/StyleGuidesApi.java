package com.crowdin.client.styleguide;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.*;
import com.crowdin.client.styleguide.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StyleGuidesApi extends CrowdinApi {
    public StyleGuidesApi(Credentials credentials) {
        super(credentials);
    }

    public StyleGuidesApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param styleGuideId style guide identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.style-guides.delete" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.style-guides.delete" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public void deleteStyleGuide(Long styleGuideId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/style-guides/" + styleGuideId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param styleGuideId style guide identifier
     * @param request request object
     * @return updated style guide
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.style-guides.patch" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.style-guides.patch" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StyleGuide> editStyleGuide(Long styleGuideId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        StyleGuideResponseObject styleGuideResponseObject = this.httpClient.patch(this.url + "/style-guides/" + styleGuideId, request, new HttpRequestConfig(), StyleGuideResponseObject.class);
        return ResponseObject.of(styleGuideResponseObject.getData());
    }

    /**
     * @param styleGuideId style guide identifier
     * @return style guide
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.style-guides.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.style-guides.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StyleGuide> getStyleGuide(Long styleGuideId) throws HttpException, HttpBadRequestException {
        StyleGuideResponseObject styleGuideResponseObject = this.httpClient.get(this.url + "/style-guides/" + styleGuideId, new HttpRequestConfig(), StyleGuideResponseObject.class);
        return ResponseObject.of(styleGuideResponseObject.getData());
    }

    /**
     * @param request request object
     * @return newly created style guide
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.style-guides.post" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.style-guides.post" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<StyleGuide> addStyleGuide(AddStyleGuideRequest request) throws HttpException, HttpBadRequestException {
        StyleGuideResponseObject styleGuideResponseObject = this.httpClient.post(this.url + "/style-guides", request, new HttpRequestConfig(), StyleGuideResponseObject.class);
        return ResponseObject.of(styleGuideResponseObject.getData());
    }

    /**
     * @param userId user identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of style guides
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.style-guides.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.style-guides.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<StyleGuide> listStyleGuide(Long userId, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        ListStyleGuidesParams params = new ListStyleGuidesParams();
        params.setUserId(userId);
        params.setLimit(limit);
        params.setOffset(offset);
        return listStyleGuide(params);
    }

    /**
     * @param userId user identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param orderBy list of OrderByField
     * @return list of style guides
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.style-guides.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.style-guides.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<StyleGuide> listStyleGuide(Long userId, Integer limit, Integer offset, List<OrderByField> orderBy) throws HttpException, HttpBadRequestException {
        ListStyleGuidesParams params = new ListStyleGuidesParams();
        params.setUserId(userId);
        params.setLimit(limit);
        params.setOffset(offset);
        params.setOrderByList(orderBy);
        return listStyleGuide(params);
    }

    public ResponseList<StyleGuide> listStyleGuide(ListStyleGuidesParams params) {
        ListStyleGuidesParams query = Optional.ofNullable(params).orElse(new ListStyleGuidesParams());

        String orderBy = query.getOrderByList() != null
                ? OrderByField.generateSortParam(query.getOrderByList())
                : query.getOrderBy();

        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "userId", Optional.ofNullable(query.getUserId()),
                "limit", Optional.ofNullable(query.getLimit()),
                "offset", Optional.ofNullable(query.getOffset()),
                "orderBy", Optional.ofNullable(orderBy)
        );
        StyleGuideResponseList styleGuideResponseList = this.httpClient.get(this.url + "/style-guides", new HttpRequestConfig(queryParams), StyleGuideResponseList.class);
        return StyleGuideResponseList.to(styleGuideResponseList);
    }
}
