package com.crowdin.client.storage;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.storage.model.Storage;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageApiTest extends TestClient {

    private final String fileName = "umbrella_app.xliff";
    private final Long id = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/storages", HttpGet.METHOD_NAME, "api/storage/listStorages.json", singletonMap("limit", 500)),
                new RequestMock(this.url + "/storages", "api/storage/test.txt", "api/storage/storage.json", HttpPost.METHOD_NAME, emptyMap(), singletonMap("Crowdin-API-FileName", fileName)),
                RequestMock.build(this.url + "/storages/" + id, HttpGet.METHOD_NAME, "api/storage/storage.json"),
                RequestMock.build(this.url + "/storages/" + id, HttpDelete.METHOD_NAME)
        );
    }

    @Test
    public void listStoragesTest() {
        ResponseList<Storage> storageResponseList = this.getStorageApi().listStorages(500, null);
        assertEquals(storageResponseList.getData().size(), 1);
        assertEquals(storageResponseList.getData().get(0).getData().getFileName(), fileName);
        assertEquals(storageResponseList.getData().get(0).getData().getId(), id);
    }

    @Test
    public void addStorageTest() {
        ResponseObject<Storage> fileContent = this.getStorageApi().addStorage(fileName, "file content 123");
        assertEquals(fileContent.getData().getId(), id);
        assertEquals(fileContent.getData().getFileName(), fileName);
    }

    @Test
    public void getStorageTest() {
        ResponseObject<Storage> fileContent = this.getStorageApi().getStorage(id);
        assertEquals(fileContent.getData().getId(), id);
        assertEquals(fileContent.getData().getFileName(), fileName);
    }

    @Test
    public void deleteStorageTest() {
        this.getStorageApi().deleteStorage(id);
    }
}
