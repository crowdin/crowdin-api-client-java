package com.crowdin.client.stringcorrections.bundles;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.stringcorrections.model.AddCorrectionRequest;
import com.crowdin.client.stringcorrections.model.Correction;
import com.crowdin.client.stringcorrections.model.ListCorrectionsQueryParams;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StringCorrectionsApiTest extends TestClient {

    private final Long projectId = 1L;
    private final Long stringId = 35434L;
    private final Long correctionId = 190695L;
    private final String text = "This string has been corrected";
    private final String pluralCategoryName = "few";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/projects/" + projectId + "/corrections", HttpGet.METHOD_NAME, "api/stringcorrections/listCorrectionsResponse.json", new HashMap<String, Long>() {{
                    put("stringId", stringId);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/corrections", HttpPost.METHOD_NAME, "api/stringcorrections/addCorrectionRequest.json", "api/stringcorrections/correction.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/corrections/" + correctionId, HttpGet.METHOD_NAME, "api/stringcorrections/correction.json"),
                RequestMock.build(this.url + "/projects/" + projectId + "/corrections/" + correctionId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/projects/" + projectId + "/corrections", HttpDelete.METHOD_NAME, new HashMap<String, Long>() {{
                    put("stringId", stringId);
                }}),
                RequestMock.build(this.url + "/projects/" + projectId + "/corrections/" + correctionId, HttpPut.METHOD_NAME, "api/stringcorrections/correction.json")
        );
    }

    @Test
    public void listCorrectionsTest() {
        ListCorrectionsQueryParams listCorrectionsQueryParams = new ListCorrectionsQueryParams();
        listCorrectionsQueryParams.setStringId(stringId);
        ResponseList<Correction> response = this.getStringCorrectionsApi().listCorrections(projectId, listCorrectionsQueryParams);
        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals(response.getData().get(0).getData().getId(), correctionId);
        assertEquals(response.getData().get(0).getData().getText(), text);
        assertEquals(response.getData().get(0).getData().getPluralCategoryName(), pluralCategoryName);
    }

    @Test
    public void addCorrectionTest() {
        AddCorrectionRequest request = new AddCorrectionRequest();
        request.setStringId(stringId);
        request.setPluralCategoryName(pluralCategoryName);
        request.setText(text);

        ResponseObject<Correction> response = this.getStringCorrectionsApi().addCorrection(projectId, request);
        assertEquals(response.getData().getText(), text);
        assertEquals(response.getData().getId(), correctionId);
        assertEquals(response.getData().getPluralCategoryName(), pluralCategoryName);
    }

    @Test
    public void getCorrectionTest() {
        ResponseObject<Correction> response = this.getStringCorrectionsApi().getCorrection(projectId, correctionId);
        assertEquals(response.getData().getText(), text);
        assertEquals(response.getData().getId(), correctionId);
        assertEquals(response.getData().getPluralCategoryName(), pluralCategoryName);
    }

    @Test
    public void deleteCorrectionTest() {
        this.getStringCorrectionsApi().deleteCorrection(projectId, correctionId);
    }

    @Test
    public void deleteCorrectionsTest() {
        this.getStringCorrectionsApi().deleteCorrections(projectId, stringId);
    }

    @Test
    public void restoreCorrectionsTest() {
        ResponseObject<Correction> response = this.getStringCorrectionsApi().restoreCorrection(projectId, correctionId);
        assertEquals(response.getData().getText(), text);
        assertEquals(response.getData().getId(), correctionId);
        assertEquals(response.getData().getPluralCategoryName(), pluralCategoryName);
    }
}
