package com.crowdin.client.clients;

import com.crowdin.client.clients.model.Client;
import com.crowdin.client.clients.model.Status;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientsApiTest extends TestClient {

    private final Long clientId = 678L;
    private final String name = "John Smith Translation Agency";
    private final String projectId = "1234"; // Example project ID
    private final String stringId = "5678";   // Example string ID
    private final String languageId = "en";    // Example language ID

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

    @Test
    public void removeStringApprovalsTest() {
        // Mock the request and response for removing string approvals
        getMocks().add(RequestMock.build(
                this.url + "/projects/" + projectId + "/strings/" + stringId + "/approvals/remove",
                HttpPost.METHOD_NAME,
                "api/strings/removeStringApprovals.json" // Mock response file
        ));

        // Call the method to test
        this.getClientsApi().removeStringApprovals(projectId, stringId);

        // Assertions can be added here if necessary
    }

    @Test
    public void deleteStringTranslationsWithLanguageIdTest() {
        // Mock the request and response for deleting string translations with language ID
        getMocks().add(RequestMock.build(
                this.url + "/projects/" + projectId + "/strings/" + stringId + "/translations/" + languageId,
                HttpDelete.METHOD_NAME,
                "api/strings/deleteStringTranslationsWithLanguageId.json" // Mock response file
        ));

        // Call the method to test
        this.getClientsApi().deleteStringTranslations(projectId, stringId, languageId);

        // Assertions can be added here if necessary
    }

    @Test
    public void deleteStringTranslationsWithoutLanguageIdTest() {
        // Mock the request and response for deleting string translations without language ID
        getMocks().add(RequestMock.build(
                this.url + "/projects/" + projectId + "/strings/" + stringId + "/translations",
                HttpDelete.METHOD_NAME,
                "api/strings/deleteStringTranslationsWithoutLanguageId.json" // Mock response file
        ));

        // Call the method to test
        this.getClientsApi().deleteStringTranslations(projectId, stringId, null);

        // Assertions can be added here if necessary
    }
}
