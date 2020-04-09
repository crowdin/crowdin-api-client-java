package com.crowdin.client.framework;

import com.crowdin.client.Client;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class TestClient extends Client {

    public TestClient() {
        super(new Credentials("testToken", "testOrganization"), ClientConfig.builder().httpClient(new TestHttpClient()).build());
    }

    @BeforeAll
    public void initializeMocks() {
        ((TestHttpClient) this.httpClient).initializeMocks(this.getMocks());
    }

    public abstract List<RequestMock> getMocks();

}
