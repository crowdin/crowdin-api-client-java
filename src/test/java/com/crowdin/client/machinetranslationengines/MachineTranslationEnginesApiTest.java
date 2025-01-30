package com.crowdin.client.machinetranslationengines;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.machinetranslationengines.model.AddMachineTranslationRequest;
import com.crowdin.client.machinetranslationengines.model.GoogleTranslateCredentials;
import com.crowdin.client.machinetranslationengines.model.MachineTranslation;
import com.crowdin.client.machinetranslationengines.model.MtTranslateRequest;
import com.crowdin.client.machinetranslationengines.model.MtTranslateResponse;
import com.crowdin.client.machinetranslationengines.model.Type;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MachineTranslationEnginesApiTest extends TestClient {

    private final Long mtId = 2L;
    private final String sourceLanguageId = "en";
    private final String name = "Crowdin Translate (beta)";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(this.url + "/mts", HttpGet.METHOD_NAME, "api/machinetranslationengines/listMts.json"),
                RequestMock.build(this.url + "/mts", HttpPost.METHOD_NAME, "api/machinetranslationengines/addMt.json", "api/machinetranslationengines/mt.json"),
                RequestMock.build(this.url + "/mts/" + mtId, HttpGet.METHOD_NAME, "api/machinetranslationengines/mt.json"),
                RequestMock.build(this.url + "/mts/" + mtId, HttpDelete.METHOD_NAME),
                RequestMock.build(this.url + "/mts/" + mtId, HttpPatch.METHOD_NAME, "api/machinetranslationengines/editMt.json", "api/machinetranslationengines/mt.json"),
                RequestMock.build(this.url + "/mts/" + mtId + "/translations", HttpPost.METHOD_NAME, "api/machinetranslationengines/mtTranslateRequest.json", "api/machinetranslationengines/mtTranslateResponse.json")

        );
    }

    @Test
    public void listMtsTest() {
        ResponseList<MachineTranslation> machineTranslationResponseList = this.getMachineTranslationEnginesApi().listMts(null, null, null);
        assertEquals(machineTranslationResponseList.getData().size(), 1);
        assertEquals(machineTranslationResponseList.getData().get(0).getData().getId(), mtId);
        assertEquals(machineTranslationResponseList.getData().get(0).getData().getName(), name);
    }

    @Test
    public void addMtTest() {
        AddMachineTranslationRequest request = new AddMachineTranslationRequest();
        request.setName(name);
        request.setType(Type.GOOGLE);
        GoogleTranslateCredentials credentials = new GoogleTranslateCredentials();
        credentials.setApiKey("string");
        request.setCredentials(credentials);
        ResponseObject<MachineTranslation> machineTranslationResponseObject = this.getMachineTranslationEnginesApi().addMt(request);
        assertEquals(machineTranslationResponseObject.getData().getId(), mtId);
        assertEquals(machineTranslationResponseObject.getData().getName(), name);
    }

    @Test
    public void getMtTest() {
        ResponseObject<MachineTranslation> machineTranslationResponseObject = this.getMachineTranslationEnginesApi().getMt(mtId);
        assertEquals(machineTranslationResponseObject.getData().getId(), mtId);
        assertEquals(machineTranslationResponseObject.getData().getName(), name);
    }

    @Test
    public void deleteMtTest() {
        this.getMachineTranslationEnginesApi().deleteMt(mtId);
    }

    @Test
    public void editMtTest() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue(name);
        request.setPath("/name");
        ResponseObject<MachineTranslation> machineTranslationResponseObject = this.getMachineTranslationEnginesApi().editMt(mtId, singletonList(request));
        assertEquals(machineTranslationResponseObject.getData().getId(), mtId);
        assertEquals(machineTranslationResponseObject.getData().getName(), name);
    }

    @Test
    public void translateViaMtTest() {
        MtTranslateRequest request = new MtTranslateRequest();
        request.setLanguageRecognitionProvider(MtTranslateRequest.LanguageRecognitionProvider.CROWDIN);
        request.setSourceLanguageId("en");
        request.setTargetLanguageId("de");
        request.setStrings(Arrays.asList("Welcome!", "Save as...", "View", "About..."));
        ResponseObject<MtTranslateResponse> responseObject = this.getMachineTranslationEnginesApi().translateViaMt(mtId, request);
        assertTrue(responseObject.getData().getSourceLanguageId().equals(sourceLanguageId));
        assertTrue(responseObject.getData().getTranslations().get(0).equals("Herzlich willkommen!"));
    }

}
