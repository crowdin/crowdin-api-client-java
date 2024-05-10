package com.crowdin.client.fields;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.fields.model.Config;
import com.crowdin.client.fields.model.Field;
import com.crowdin.client.fields.model.FieldRequest;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class FieldsApiTest extends TestClient {
    private final Long fieldId = 1L;
    private final String fieldName = "Custom field";
    private final String entity = "task";
    private final int limit = 5;
    private final int offset = 0;
    private final String fullUrl = this.url + "/fields";

    @Override
    public List<RequestMock> getMocks() {
        String resourcesDir = "api/fields/";
        return Arrays.asList(
                RequestMock.build(fullUrl, HttpGet.METHOD_NAME, resourcesDir + "fieldList.json",
                        new HashMap<String, Object>() {{
                            put("entity", entity);
                            put("search", fieldName);
                            put("limit", limit);
                            put("offset", offset);
                        }}),
                RequestMock.build(fullUrl, HttpPost.METHOD_NAME, resourcesDir + "fieldsRequest.json",
                        resourcesDir + "fieldsResponse.json"),
                RequestMock.build(fullUrl + "/" + fieldId, HttpGet.METHOD_NAME, resourcesDir + "fieldList.json"),
                RequestMock.build(fullUrl + "/" + fieldId, HttpDelete.METHOD_NAME),
                RequestMock.build(fullUrl + "/" + fieldId, HttpPatch.METHOD_NAME, "api/fields/editFieldRequest.json", "api/fields/field.json"));

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
        List<String> entityTypes = Collections.singletonList("task");
        FieldRequest request = new FieldRequest();
        request.setName(fieldName);
        request.setSlug("custom-field");
        request.setType("select");
        request.setDescription("Client company field is appointed to store infor about client company");
        request.setEntities(entityTypes);

        Config config = new Config();

        Config.Option option = new Config.Option();
        option.setLabel("Test field");
        option.setValue("Test value");

        Config.Location location = new Config.Location();
        location.setPlace("Test place");

        List<Config.Location> locations = new ArrayList<>();
        locations.add(location);

        List<Config.Option> options = new ArrayList<>();
        options.add(option);

        config.setOptions(options);
        config.setLocations(locations);

        request.setConfig(config);

        ResponseObject<Field> fieldResponseObject = this.getFieldsApi().addField(request);
        assertEquals(fieldResponseObject.getData().getId(), 2);
        assertEquals(fieldResponseObject.getData().getName(), fieldName);
    }

    @Test
    void getField() {
        ResponseList<Field> fields = this.getFieldsApi().getField(fieldId);
        assertEquals(fields.getData().size(), 1);
        assertEquals(fields.getData().get(0).getData().getId(), fieldId);
        assertEquals(fields.getData().get(0).getData().getName(), fieldName);
    }

    @Test
    void deleteField() {
        try {
            this.getFieldsApi().deleteField(fieldId);
            // If no exception is thrown, the delete operation is successful
        } catch (HttpBadRequestException e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void editField() {
        PatchRequest request = new PatchRequest();
        request.setOp(PatchOperation.REPLACE);
        request.setValue("custom-field-request");
        request.setPath("name");
        ResponseObject<Field> responseObject = this.getFieldsApi().editField(fieldId, singletonList(request));
        assertEquals(responseObject.getData().getId(), fieldId);
        assertEquals(responseObject.getData().getName(), fieldName);
    }
}