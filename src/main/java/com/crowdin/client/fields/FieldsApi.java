package com.crowdin.client.fields;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.fields.model.Field;
import com.crowdin.client.fields.model.FieldRequest;
import com.crowdin.client.fields.model.FieldResponseObject;
import com.crowdin.client.fields.model.FieldResponseObjectList;

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
     * @param limit     maximum number of items to retrieve (default 25)
     * @param offset    starting offset in the collection (default 0)
     * @return list of distributions
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.fields.getMany" target="_blank"><b>API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Field>listFields(String entity, String search, Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "entity", Optional.ofNullable(entity),
                "search", Optional.ofNullable(search),
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        FieldResponseObjectList responseObject = this.httpClient.get(this.url + "/fields", new HttpRequestConfig(queryParams), FieldResponseObjectList.class);
        return FieldResponseObjectList.to(responseObject);
    }

    public ResponseObject<Field> addField(FieldRequest request) throws HttpException, HttpBadRequestException {
        FieldResponseObject reportStatusResponseObject = this.httpClient.post(this.url + "/fields", request, new HttpRequestConfig(), FieldResponseObject.class);
        return ResponseObject.of(reportStatusResponseObject.getData());
    }
}
