package com.crowdin.client.securitylogs;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.securitylogs.model.SecurityLogResource;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityLogsTest extends TestClient {

    private final long userId = 4;
    private final long securityLogId = 5;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/users/" + userId + "/security-logs", HttpGet.METHOD_NAME, "api/securitylogs/listSecurityLogs.json"),
                RequestMock.build(this.url + "/users/" + userId + "/security-logs" + securityLogId, HttpGet.METHOD_NAME, "api/securitylogs/getSecurityLogBylogId.json")
        );
    }

    @Test
    public void listSecurityLogs(){
        ResponseList<SecurityLogResource> securityLogResourceResponseList = this.getSecurityLogsApi().listSecurityLogs(userId,null, null, null, null);
        assertEquals(securityLogResourceResponseList.getData().size(),2);
        assertEquals(securityLogResourceResponseList.getData().get(0).getData().getUserId(),userId);
    }

    @Test
    public  void getSecurityLogByLogId(){
        ResponseObject<SecurityLogResource> securityLogResourceResponseObject = this.getSecurityLogsApi().getUserSecurityLog(userId,securityLogId);
        assertEquals(securityLogResourceResponseObject.getData().getId(), securityLogId);
    }

}
