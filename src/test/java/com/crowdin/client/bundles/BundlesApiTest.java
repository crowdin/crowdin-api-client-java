package com.crowdin.client.bundles;

import com.crowdin.client.bundles.model.*;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BundlesApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long projectId2 = 2L;
    private final Long bundleId = 14L;
    private final Long fileInfoCollectionResourceId = 44L;
    private final Long fileCollectionResourceId = 43L;
    private final String name = "Resx bundle";
    private final String bundleResourceName = "umbrella_app.xliff";
    private final String format = "crowdin-resx";
    private final String pattern = "strings-%two_letter_code%.resx";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles", HttpGet.METHOD_NAME, "api/bundles/listBundlesResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles", HttpPost.METHOD_NAME, "api/bundles/addBundleRequest.json", "api/bundles/bundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId, HttpGet.METHOD_NAME, "api/bundles/bundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId, HttpPatch.METHOD_NAME, "api/bundles/editBundle.json", "api/bundles/bundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + fileInfoCollectionResourceId + "/files", HttpGet.METHOD_NAME, "api/bundles/fileInfoCollectionResource.json"),
                RequestMock.build(this.url + "/projects/" + projectId2 + "/bundles/" + fileCollectionResourceId + "/files", HttpGet.METHOD_NAME, "api/bundles/fileCollectionResource.json")
        );
    }

    @Test
    public void listBundlesTest() {
        ResponseList<Bundle> response = this.getBundlesApi().listBundles(projectId);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals(response.getData().get(0).getData().getId(), projectId);
    }

    @Test
    public void addBundleTest() {
        Bundle request = new Bundle();
        request.setName(name);
        request.setFormat(format);
        request.setSourcePatterns(Collections.singletonList("/master/"));
        request.setIgnorePatterns(Collections.singletonList("/master/environments/"));
        request.setExportPattern(pattern);
        request.setLabelIds(Collections.singletonList(0L));

        ResponseObject<Bundle> response = this.getBundlesApi().addBundle(projectId, request);
        assertEquals(response.getData().getFormat(), format);
        assertEquals(response.getData().getName(), name);
        assertEquals(response.getData().getId(), projectId);
    }

    @Test
    public void getBundleTest() {
        ResponseObject<Bundle> response = this.getBundlesApi().getBundle(projectId, bundleId);
        assertEquals(response.getData().getId(), projectId);
        assertEquals(response.getData().getName(), name);
    }

    @Test
    public void deleteBundleTest() {
        this.getBundlesApi().deleteBundle(projectId, bundleId);
    }

    @Test
    public void editBundleTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setPath("/name");
        ResponseObject<Bundle> response = this.getBundlesApi().editBundle(projectId, bundleId, Arrays.asList(request));
        assertEquals(response.getData().getId(), projectId);
        assertEquals(response.getData().getName(), name);
    }

    @Test
    public void listFileInfoCollectionResourceTest() {
        ResponseList<? extends FileInfoCollectionResourceResponse> response = this.getBundlesApi().listBundleFiles(projectId, fileInfoCollectionResourceId, null,null);
        assertEquals(response.getData().get(0).getData().getId(), fileInfoCollectionResourceId);
        assertEquals(response.getData().get(0).getData().getName(), bundleResourceName);
    }

    @Test
    public void listFileCollectionResourceTest() {
        ResponseList<? extends FileInfoCollectionResourceResponse> response = this.getBundlesApi().listBundleFiles(projectId2, fileCollectionResourceId, null,null);
        assertTrue(response.getData().get(0).getData() instanceof FileInfoCollectionResourceResponse);
        System.out.println(response.getData().get(0).getData() instanceof FileInfoCollectionResourceResponse);
        FileInfoCollectionResourceResponse fileCollectionResourceResponse;
        fileCollectionResourceResponse = response.getData().get(0).getData();
        assertEquals(fileCollectionResourceResponse.getId(), fileCollectionResourceId);
        assertEquals(fileCollectionResourceResponse.getName(), bundleResourceName);
    }

}
