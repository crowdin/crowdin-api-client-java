package com.crowdin.client.fields;

import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.fields.model.Field;
import com.crowdin.client.fields.model.FieldRequest;
import com.crowdin.client.fields.model.enums.EntityType;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.reports.model.*;
import com.crowdin.client.reports.model.Currency;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldsApiTest extends TestClient {
    private final Long fieldId = 1L;
    private final String fieldName = "Custom field";
    private final String fieldSlug = "custom-field";
    private final String entity = "select";
    private final String type = "text";
    private final int limit = 5;
    private final int offset = 0;
    private final String fullUrl = this.url + "/fields";
    private final String resourcesDir = "api/fields/";

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                RequestMock.build(fullUrl, HttpGet.METHOD_NAME, resourcesDir + "fieldList.json",
                        new HashMap<String, Object>() {{
                            put("entity", entity);
                            put("search", fieldName);
                            put("limit", limit);
                            put("offset", offset);
                        }}),
                RequestMock.build(fullUrl, HttpPost.METHOD_NAME, resourcesDir + "fieldsRequest.json",
                        resourcesDir + "fieldsResponse.json"));
    }

    @Test
    void listFields() {
        ResponseList<Field> fields = this.getFieldsApi().listFields(entity, fieldName, limit, offset);
        assertEquals(fields.getData().size(), 1);
        assertEquals(fields.getData().get(0).getData().getId(), fieldId);
        assertEquals(fields.getData().get(0).getData().getName(), fieldName);
    }

    @Test
    public void addField() {
        /* TimeZone.setDefault(tz);*/
        List<String> entityTypes = Collections.singletonList("tasks");
        FieldRequest request = new FieldRequest();
        request.setName("Client company");
        request.setSlug("client-company");
        request.setType("select");
        request.setEntities(entityTypes);

        /*CostEstimateGenerateReportRequest.Schema schema = new CostEstimateGenerateReportRequest.Schema();
        schema.setUnit(Unit.WORDS);
        schema.setCurrency(Currency.USD);
        schema.setLanguageId("ach");
        schema.setFormat(ReportsFormat.XLSX);
        CostEstimateGenerateReportRequest.TranslateStep translateStep = new CostEstimateGenerateReportRequest.TranslateStep();
        translateStep.setMode("simple");
        translateStep.setType("Translate");
        CostEstimateGenerateReportRequest.TranslateRegularRate regularRate = new CostEstimateGenerateReportRequest.TranslateRegularRate();
        regularRate.setMode(CostEstimateGenerateReportRequest.Mode.TM_MATCH);
        regularRate.setValue(0.1);
        translateStep.setRegularRates(Collections.singletonList(regularRate));
        CostEstimateGenerateReportRequest.TranslateIndividualRate individualRate = new CostEstimateGenerateReportRequest.TranslateIndividualRate();
        individualRate.setLanguageIdsTo(Collections.singletonList("uk"));
        individualRate.setRates(Collections.singletonList(regularRate));
        translateStep.setIndividualRates(Collections.singletonList(individualRate));
        schema.setStepTypes(Collections.singletonList(translateStep));
        request.setSchema(schema);*/

        ResponseObject<Field> fieldResponseObject = this.getFieldsApi().addField(request);
        assertEquals(fieldResponseObject.getData().getId(), 2);
        assertEquals(fieldResponseObject.getData().getName(), fieldName);
    }
}