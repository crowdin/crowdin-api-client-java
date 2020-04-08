package com.crowdin.client.vendors;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.vendors.model.Vendor;
import com.crowdin.client.vendors.model.VendorResponseList;

import java.util.Map;
import java.util.Optional;

public class VendorsApi extends CrowdinApi {
    public VendorsApi(Credentials credentials) {
        super(credentials);
    }

    public VendorsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param limit  maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @return list of vendors
     */
    public ResponseList<Vendor> listVendors(Integer limit, Integer offset) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Integer>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset)
        );
        VendorResponseList vendorResponseList = this.httpClient.get(this.url + "/vendors", new HttpRequestConfig(queryParams), VendorResponseList.class);
        return VendorResponseList.to(vendorResponseList);
    }
}
