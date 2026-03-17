package com.crowdin.client.styleguide;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.styleguide.model.AddStyleGuideRequest;
import com.crowdin.client.styleguide.model.StyleGuide;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StyleGuidesApiTest extends TestClient {

    private final Long styleGuideId = 2L;
    private final Long storageId = 1L;
    private final String name = "Be My Eyes iOS's Style Guide";
    private final String aiInstructions = "string";
    private final List<String> languageIds = Arrays.asList("uk", "fr", "de");
    private final List<Long> projectIds = Arrays.asList(1L, 2L, 3L);
    private final Boolean isShared = false;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/style-guides", HttpGet.METHOD_NAME, "api/styleguide/listStyleGuides.json"),
                RequestMock.build(this.url + "/style-guides", HttpPost.METHOD_NAME, "api/styleguide/addStyleGuideRequest.json", "api/styleguide/styleGuide.json"),
                RequestMock.build(this.url + "/style-guides/" + styleGuideId, HttpGet.METHOD_NAME, "api/styleguide/styleGuide.json"),
                RequestMock.build(this.url + "/style-guides/" + styleGuideId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/style-guides/" + styleGuideId, HttpPatch.METHOD_NAME, "api/glossaries/editGlossary.json", "api/styleguide/styleGuide.json")
        );
    }

    @Test
    public void listStyleGuidesTest() {
        ResponseList<StyleGuide> styleGuideResponseList = this.getStyleGuidesApi().listStyleGuide(null, null, null);
        assertEquals(styleGuideResponseList.getData().size(), 1);
        assertEquals(styleGuideResponseList.getData().get(0).getData().getId(), styleGuideId);
        assertEquals(styleGuideResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addStyleGuideTest() {
        AddStyleGuideRequest request = new AddStyleGuideRequest();
        request.setName(name);
        request.setAiInstructions(aiInstructions);
        request.setLanguageIds(languageIds);
        request.setProjectIds(projectIds);
        request.setIsShared(isShared);
        request.setStorageId(storageId);
        ResponseObject<StyleGuide> styleGuideResponseObject = this.getStyleGuidesApi().addStyleGuide(request);
        assertEquals(styleGuideResponseObject.getData().getId(), styleGuideId);
        assertEquals(styleGuideResponseObject.getData().getName(), name);
    }

    @Test
    public void getStyleGuideTest() {
        ResponseObject<StyleGuide> styleGuideResponseObject = this.getStyleGuidesApi().getStyleGuide(styleGuideId);
        assertEquals(styleGuideResponseObject.getData().getId(), styleGuideId);
        assertEquals(styleGuideResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteStyleGuideTest() {
        this.getStyleGuidesApi().deleteStyleGuide(styleGuideId);
    }

    @Test
    public void editStyleGuideTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<StyleGuide> styleGuideResponseObject = this.getStyleGuidesApi().editStyleGuide(styleGuideId, singletonList(request));
        assertEquals(styleGuideResponseObject.getData().getId(), styleGuideId);
        assertEquals(styleGuideResponseObject.getData().getName(), name);
    }
}
