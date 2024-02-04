package com.crowdin.client.securitylogs;

import com.crowdin.client.core.CrowdinApi;
import com.crowdin.client.core.http.HttpRequestConfig;
import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.securitylogs.model.SecurityLogResource;
import com.crowdin.client.securitylogs.model.SecurityLogResourceObject;
import com.crowdin.client.securitylogs.model.SecurityLogsResourceResponseList;

import java.util.Map;
import java.util.Optional;

public class SecurityLogsApi extends CrowdinApi {

    public SecurityLogsApi(Credentials credentials) {
        super(credentials);
    }

    public SecurityLogsApi(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
    }

    /**
     * @param userId user Identifier
     * @param limit maximum number of items to retrieve (default 25)
     * @param offset starting offset in the collection (default 0)
     * @param event value of event
     * @param ipAddress value of ipAddress
     * @return list of Security logs
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.security-logs.getMany" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.security-logs.getMany" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseList<SecurityLogResource> listSecurityLogs(Long userId, Long limit, Integer offset, String event, String ipAddress) throws HttpException, HttpBadRequestException {
        Map<String, Optional<Object>> queryParams = HttpRequestConfig.buildUrlParams(
                "limit", Optional.ofNullable(limit),
                "offset", Optional.ofNullable(offset),
                "event", Optional.ofNullable(event),
                "ipAddress", Optional.ofNullable(ipAddress)
        );
        SecurityLogsResourceResponseList securityLogsResponseList = this.httpClient.get(this.url + "/users/" + userId+ "/security-logs", new HttpRequestConfig(queryParams), SecurityLogsResourceResponseList.class);
        return SecurityLogsResourceResponseList.to(securityLogsResponseList);
    }

    /**
     * @param userId user Identifier
     * @param securityLogId log Identifier
     * @return Security log
     * @see <ul>
     * <li><a href="https://developer.crowdin.com/api/v2/#operation/api.users.security-logs.get" target="_blank"><b>API Documentation</b></a></li>
     * <li><a href="https://developer.crowdin.com/enterprise/api/v2/#operation/api.users.security-logs.get" target="_blank"><b>Enterprise API Documentation</b></a></li>
     * </ul>
     */
    public ResponseObject<SecurityLogResource> getUserSecurityLog(Long userId, Long securityLogId) throws HttpException, HttpBadRequestException {
        SecurityLogResourceObject securityLogResourceObject = this.httpClient.get(this.url + "/users/" + userId+ "/security-logs" + securityLogId, new HttpRequestConfig(), SecurityLogResourceObject.class);
        return ResponseObject.of(securityLogResourceObject.getData());
    }


}
