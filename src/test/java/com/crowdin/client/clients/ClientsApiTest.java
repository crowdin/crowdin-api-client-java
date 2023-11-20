package com.crowdin.client.clients;

import com.crowdin.client.clients.model.Client;
import com.crowdin.client.clients.model.Status;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientsApiTest extends TestClient {

    private final Long clientId = 678L;
    private final String name = "John Smith Translation Agency";

    @Override
    public List<RequestMock> getMocks() {
        return singletonList(
                RequestMock.build(this.url + "/clients", HttpGet.METHOD_NAME, "api/clients/listClients.json")
        );
    }

    @Test
    public void listClientsTest() {
        ResponseList<Client> clientResponseList = this.getClientsApi().listClients(null, null);
        assertEquals(clientResponseList.getData().size(), 1);
        assertEquals(clientResponseList.getData().get(0).getData().getId(), clientId);
        assertEquals(clientResponseList.getData().get(0).getData().getName(), name);
        assertEquals(clientResponseList.getData().get(0).getData().getStatus(), Status.PENDING);
    }
}
