package com.crowdin.client.dictionaries;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.dictionaries.model.Dictionary;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DictionariesApiTest extends TestClient {

    private final Long projectId = 42L;
    private final String languageId = "en";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
            RequestMock.build(String.format("%s/projects/%d/dictionaries", this.url, projectId), HttpGet.METHOD_NAME,
                "api/dictionaries/listDictionariesResponse.json"),
            RequestMock.build(String.format("%s/projects/%d/dictionaries/%s", this.url, projectId, languageId), HttpPatch.METHOD_NAME,
                "api/dictionaries/editDictionaryRequest.json", "api/dictionaries/editDictionaryResponse.json")
        );
    }

    @Test
    public void listDictionariesTest() {
        ResponseList<Dictionary> response = this.getDictionariesApi().listDictionaries(this.projectId, null);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
    }

    @Test
    public void editDictionaryTest() {
        List<PatchRequest> patches = Arrays.asList(
            new PatchRequest() {{
                setOp(PatchOperation.REMOVE);
                setPath("/words/0");
            }}
        );
        ResponseObject<Dictionary> response = this.getDictionariesApi().editDictionary(this.projectId, this.languageId, patches);
        assertNotNull(response);
    }
}
