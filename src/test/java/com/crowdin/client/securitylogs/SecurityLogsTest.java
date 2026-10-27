package com.crowdin.client.securitylogs;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.securitylogs.model.SecurityLogResource;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityLogsTest extends TestClient {

    private final long userId = 4;
    private final long securityLogId = 5;
    private final String createdAfter = "2023-09-20T11:34:40+00:00";
    private final String createdBefore = "2023-09-23T11:34:40+00:00";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/users/" + userId + "/security-logs", HttpGet.METHOD_NAME, "api/securitylogs/listSecurityLogs.json"),
                RequestMock.build(this.url + "/users/" + userId + "/security-logs", HttpGet.METHOD_NAME, "api/securitylogs/listSecurityLogs.json", new HashMap<String, Object>() {{
                    put("createdAfter", createdAfter);
                    put("createdBefore", createdBefore);
                }}),
                RequestMock.build(this.url + "/users/" + userId + "/security-logs/" + securityLogId, HttpGet.METHOD_NAME, "api/securitylogs/getSecurityLogBylogId.json"),
                RequestMock.build(this.url + "/security-logs", HttpGet.METHOD_NAME, "api/securitylogs/listOrganizationLogs.json"),
                RequestMock.build(this.url + "/security-logs/" + securityLogId, HttpGet.METHOD_NAME, "api/securitylogs/getOrganizationLogBylogId.json")

        );
    }

    @Test
    public void listSecurityLogs(){
        ResponseList<SecurityLogResource> securityLogResourceResponseList = this.getSecurityLogsApi().listUserSecurityLogs(userId,null, null, null, null);
        assertEquals(securityLogResourceResponseList.getData().size(),2);
        assertEquals(securityLogResourceResponseList.getData().get(0).getData().getUserId(),userId);
    }

    @Test
    public void listSecurityLogsWithDateRange() {
        ResponseList<SecurityLogResource> securityLogResourceResponseList = this.getSecurityLogsApi().listUserSecurityLogs(userId, null, null, null, createdAfter, createdBefore, null);
        assertEquals(2, securityLogResourceResponseList.getData().size());
        assertEquals(userId, securityLogResourceResponseList.getData().get(0).getData().getUserId());
    }

    @Test
    public  void getSecurityLogByLogId(){
        ResponseObject<SecurityLogResource> securityLogResourceResponseObject = this.getSecurityLogsApi().getUserSecurityLog(userId,securityLogId);
        assertEquals(securityLogResourceResponseObject.getData().getId(), securityLogId);
    }
    
    @Test
    public void listOrganizationLogs(){
        ResponseList<SecurityLogResource> securityLogResourceResponseList =  this.getSecurityLogsApi().listOrganizationSecurityLogs(null,null,null,null,null,null,null);
        assertEquals(securityLogResourceResponseList.getData().size(), 2);
    }
    
    @Test
    public void getOrganizationLogbylogId(){
        ResponseObject<SecurityLogResource> securityLogResourceResponseObject = this.getSecurityLogsApi().getOrganizationSecurityLog(securityLogId);
        assertEquals(securityLogResourceResponseObject.getData().getId(), securityLogId);
    }

}
