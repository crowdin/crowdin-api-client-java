package com.crowdin.client.bundles;

import com.crowdin.client.bundles.model.*;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.sourcefiles.model.Branch;
import com.crowdin.client.sourcefiles.model.File;
import com.crowdin.client.sourcefiles.model.FileInfo;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class BundlesApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long projectId2 = 2L;
    private final Long bundleId = 14L;
    private final Long fileInfoCollectionResourceId = 44L;
    private final Long fileCollectionResourceId = 43L;
    private final Long branchCollectionResourceId = 44L;
    private final Long branchId = 45L;
    private final String branchName = "dev";
    private final String name = "Resx bundle";
    private final String bundleResourceName = "umbrella_app.xliff";
    private final String format = "crowdin-resx";
    private final String pattern = "strings-%two_letter_code%.resx";
    private final String exportId = "50fb3506-4127-4ba8-8296-f97dc7e3e0c3";
    private final String status = "finished";
    private final TimeZone tz = TimeZone.getTimeZone("GMT");

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles", HttpGet.METHOD_NAME, "api/bundles/listBundlesResponse.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles", HttpPost.METHOD_NAME, "api/bundles/addBundleRequest.json", "api/bundles/bundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId, HttpGet.METHOD_NAME, "api/bundles/bundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId, HttpPatch.METHOD_NAME, "api/bundles/editBundle.json", "api/bundles/bundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + fileInfoCollectionResourceId + "/files", HttpGet.METHOD_NAME, "api/bundles/fileInfoCollectionResource.json"),
                RequestMock.build(this.url + "/projects/" + projectId2 + "/bundles/" + fileCollectionResourceId + "/files", HttpGet.METHOD_NAME, "api/bundles/fileCollectionResource.json"),
                RequestMock.build(this.url + "/projects/" + projectId2 + "/bundles/" + branchCollectionResourceId + "/branches", HttpGet.METHOD_NAME, "api/bundles/branchCollectionResource.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/exports/" + exportId + "/download", HttpGet.METHOD_NAME, "api/bundles/downloadBundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/exports", HttpPost.METHOD_NAME, "api/bundles/exportBundle.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/bundles/" + bundleId + "/exports/" + exportId, HttpGet.METHOD_NAME, "api/bundles/exportBundle.json")
        );
    }

    @Test
    public void listBundlesTest() {
        ResponseList<Bundle> response = this.getBundlesApi().listBundles(projectId);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals(response.getData().get(0).getData().getId(), projectId);
        assertFalse(response.getData().get(0).getData().isMultilingual());
    }

    @Test
    public void addBundleTest() {
        AddBundleRequest request = new AddBundleRequest();
        request.setName(name);
        request.setFormat(format);
        request.setSourcePatterns(Collections.singletonList("/master/"));
        request.setIgnorePatterns(Collections.singletonList("/master/environments/"));
        request.setExportPattern(pattern);
        request.setIsMultilingual(true);
        request.setIncludeProjectSourceLanguage(true);
        request.setIncludeInContextPseudoLanguage(false);
        request.setLabelIds(Collections.singletonList(0L));

        ResponseObject<Bundle> response = this.getBundlesApi().addBundle(projectId, request);
        assertEquals(response.getData().getFormat(), format);
        assertEquals(response.getData().getName(), name);
        assertEquals(response.getData().getId(), projectId);
        assertTrue(response.getData().isMultilingual());
    }

    @Test
    public void getBundleTest() {
        ResponseObject<Bundle> response = this.getBundlesApi().getBundle(projectId, bundleId);
        assertEquals(response.getData().getId(), projectId);
        assertEquals(response.getData().getName(), name);
        assertTrue(response.getData().isMultilingual());
        assertTrue(response.getData().getIncludeProjectSourceLanguage());
    }

    @Test
    public void deleteBundleTest() {
        this.getBundlesApi().deleteBundle(projectId, bundleId);
    }

    @Test
    public void editBundleTest() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/name");
                setValue("New name");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/includeProjectSourceLanguage");
                setValue(true);
            }});
        }};

        ResponseObject<Bundle> response = this.getBundlesApi().editBundle(projectId, bundleId, request);
        assertEquals(response.getData().getId(), projectId);
        assertEquals(response.getData().getName(), name);
    }

    @Test
    public void listFileInfoCollectionResourceTest() {
        ResponseList<? extends FileInfo> response = this.getBundlesApi().listBundleFiles(projectId, fileInfoCollectionResourceId, null,null);
        assertEquals(response.getData().get(0).getData().getId(), fileInfoCollectionResourceId);
        assertEquals(response.getData().get(0).getData().getName(), bundleResourceName);
    }

    @Test
    public void listFileCollectionResourceTest() {
        ResponseList<? extends FileInfo> response = this.getBundlesApi().listBundleFiles(projectId2, fileCollectionResourceId, null,null);
        FileInfo fileCollectionResourceResponse;
        fileCollectionResourceResponse = response.getData().get(0).getData();
        assertTrue(fileCollectionResourceResponse instanceof File);
        assertEquals(fileCollectionResourceResponse.getId(), fileCollectionResourceId);
        assertEquals(fileCollectionResourceResponse.getName(), bundleResourceName);
    }

    @Test
    public void listBranchCollectionResourceTest() {
        ResponseList<Branch> response = this.getBundlesApi().listBundleBranches(projectId2, branchCollectionResourceId, null,null);
        Branch branch = response.getData().get(0).getData();
        assertTrue(branch instanceof Branch);
        assertEquals(branch.getId(), branchId);
        assertEquals(branch.getName(), branchName);
    }

    @Test
    public void downloadBundleTest() {
        TimeZone.setDefault(tz);
        ResponseObject<DownloadLink> response = this.getBundlesApi().downloadBundle(projectId, bundleId, exportId);
        assertEquals(new Date(119, Calendar.SEPTEMBER, 20,10,31,21), response.getData().getExpireIn());
        assertEquals("test.com", response.getData().getUrl());
    }

    @Test
    public void exportBundleTest() {
        ResponseObject<BundleExport> response = this.getBundlesApi().exportBundle(projectId, bundleId);
        assertEquals(exportId, response.getData().getIdentifier());
        assertEquals(2,response.getData().getAttributes().getBundleId());
    }

    @Test
    public void checkBundleExportStatusTest() {
        ResponseObject<BundleExport> response = this.getBundlesApi().checkBundleExportStatus(projectId, bundleId, exportId);
        assertEquals(exportId, response.getData().getIdentifier());
        assertEquals(status, response.getData().getStatus());
    }
}
