package com.crowdin.client.clients;

import com.crowdin.client.clients.model.Client;
import com.crowdin.client.clients.model.ClientResponseList;
import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;

import java.util.Map;
import java.util.Optional;

public class ClientsApi extends CrowdinApi {
    public ClientsApi(Credentials credentials) {
        super(credentials);
    }

    public ClientsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of vendors
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.clients.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<Client> listClients(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        ClientResponseList vendorResponseList = this.httpClient.get(this.url + "/clients", new HttpRequestConfig(queryParams), ClientResponseList.class);
        return ClientResponseList.to(vendorResponseList);
    }
}
