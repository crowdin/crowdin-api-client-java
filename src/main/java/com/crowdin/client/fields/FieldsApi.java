package com.crowdin.client.fields;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.fields.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FieldsApi extends CrowdinApi {

    public FieldsApi(Credentials credentials) {
        super(credentials);
    }

    public FieldsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param entity Filter fields by entity {@link com.crowdin.client.fields.model.enums.EntityType}
     * @param search Search fields by slug or name
     * @param limit  maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of distributions
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.fields.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Field> listFields(String entity, String search, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "entity", Optional.ofNullable(entity),
                "search", Optional.ofNullable(search),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FieldResponseObjectList responseObject = this.httpClient.get(this.url + "/fields", new HttpRequestConfig(queryParams), FieldResponseObjectList.class);
        return FieldResponseObjectList.to(responseObject);
    }

    public ResponseList<Field> listFields(ListFieldsParams params) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "entity", Optional.ofNullable(params.getEntity()),
                "search", Optional.ofNullable(params.getSearch()),
                "limit", Optional.ofNullable(params.getLimit()),
                "type", Optional.ofNullable(params.getType()),
                "offset", Optional.ofNullable(params.getOffset())
        );
        FieldResponseObjectList responseObject = this.httpClient.get(this.url + "/fields", new HttpRequestConfig(queryParams), FieldResponseObjectList.class);
        return FieldResponseObjectList.to(responseObject);
    }

    /**
     * @param request object {@link com.crowdin.client.fields.model.FieldRequest}
     * @return Field object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.fields.post" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Field> addField(FieldRequest request) throws HttpException, HttpBadRequestException {
        FieldResponseObject reportStatusResponseObject = this.httpClient.post(this.url + "/fields", request, new HttpRequestConfig(), FieldResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }

    /**
     * @param fieldId field identifier
     * @return field object according to id
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.fields.get" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Field> getField(Long fieldId) throws HttpException, HttpBadRequestException {
        FieldResponseObjectList responseObject = this.httpClient.get(this.url + "/fields/" + fieldId, new HttpRequestConfig(), FieldResponseObjectList.class);
        return FieldResponseObjectList.to(responseObject);
    }

    /**
     * @param fieldId field identifier
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.fields.delete" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public void deleteField(Long fieldId) throws HttpException, HttpBadRequestException {
        this.httpClient.delete(this.url + "/fields/" + fieldId, new HttpRequestConfig(), Void.class);
    }

    /**
     * @param fieldId field identifier
     * @param request object
     * @return field object
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.fields.patch" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<Field> editField(Long fieldId, List<PatchRequest> request) throws HttpException, HttpBadRequestException {
        FieldResponseObject responseObject = this.httpClient.patch(this.url + "/fields/" + fieldId, request, new HttpRequestConfig(), FieldResponseObject.class);
        return ResponseObject.of(responseObject.getData());
    }
}
