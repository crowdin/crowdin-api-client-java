package com.crowdin.client.distributions;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.distributions.model.AddDistributionRequest;
import com.crowdin.client.distributions.model.Distribution;
import com.crowdin.client.distributions.model.DistributionRelease;
import com.crowdin.client.distributions.model.ExportMode;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistributionsApiTest extends TestClient {

    private final Long projectId = 3L;
    private final String hash = "asccvfd";
    private final String name = "distribution 1";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions", HttpGet.METHOD_NAME, "api/distributions/listDistributions.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions", HttpPost.METHOD_NAME, "api/distributions/addDistribution.json", "api/distributions/distribution.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions/" + hash, HttpGet.METHOD_NAME, "api/distributions/distribution.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions/" + hash, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions/" + hash, HttpPatch.METHOD_NAME, "api/distributions/editDistribution.json", "api/distributions/distribution.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions/" + hash + "/release", HttpGet.METHOD_NAME, "api/distributions/release.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/distributions/" + hash + "/release", HttpPost.METHOD_NAME, "api/distributions/releaseRequest.json", "api/distributions/release.json")
        );
    }

    @Test
    public void listDistributionsTest() {
        ResponseList<Distribution> distributionResponseList = this.getDistributionsApi().listDistributions(projectId, null, null);
        assertEquals(distributionResponseList.getData().size(), 1);
        assertEquals(distributionResponseList.getData().get(0).getData().getHash(), hash);
        assertEquals(distributionResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addDistributionTest() {
        AddDistributionRequest request = new AddDistributionRequest();
        request.setExportMode(ExportMode.BUNDLE);
        request.setName(name);
        request.setFormat("crowdin-resx");
        request.setExportPattern("strings-%two_letter_code%.resx");
        request.setFileIds(Collections.singletonList(0L));
        request.setLabelIds(Collections.singletonList(0L));
        ResponseObject<Distribution> distributionResponseObject = this.getDistributionsApi().addDistribution(projectId, request);
        assertEquals(distributionResponseObject.getData().getHash(), hash);
        assertEquals(distributionResponseObject.getData().getName(), name);
        assertEquals(distributionResponseObject.getData().getExportMode(), "bundle");
    }

    @Test
    public void getDistributionTest() {
        ResponseObject<Distribution> distributionResponseObject = this.getDistributionsApi().getDistribution(projectId, hash);
        assertEquals(distributionResponseObject.getData().getHash(), hash);
        assertEquals(distributionResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteDistributionTest() {
        this.getDistributionsApi().deleteDistribution(projectId, hash);
    }

    @Test
    public void editDistributionTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<Distribution> distributionResponseObject = this.getDistributionsApi().editDistribution(projectId, hash, Arrays.asList(request));
        assertEquals(distributionResponseObject.getData().getHash(), hash);
        assertEquals(distributionResponseObject.getData().getName(), name);
    }

    @Test
    public void getDistributionReleaseTest() {
        ResponseObject<DistributionRelease> distributionRelease = this.getDistributionsApi().getDistributionRelease(projectId, hash);
        assertEquals(distributionRelease.getData().getProgress(), new Integer(100));
    }

    @Test
    public void createDistributionReleaseTest() {
        ResponseObject<DistributionRelease> distributionRelease = this.getDistributionsApi().createDistributionRelease(projectId, hash);
        assertEquals(distributionRelease.getData().getProgress(), new Integer(100));
    }
}
