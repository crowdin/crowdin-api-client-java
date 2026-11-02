package com.crowdin.client.applications;

import com.crowdin.client.applications.integrations.model.IntegrationLoginRequest;
import com.crowdin.client.applications.integrations.model.UpdateIntegrationSettingsRequest;
import com.crowdin.client.applications.storage.model.AddApplicationKvRecordRequest;
import com.crowdin.client.applications.storage.model.ApplicationKvRecord;
import com.crowdin.client.applications.storage.model.ApplicationKvRecordPatchRequest;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.PatchOperation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Secret values must not leak through {@code toString()} (e.g. into logs), while the JSON payload stays unchanged.
 */
class SensitiveDataToStringTest {

    private static final String SECRET = "s3cr3t-token-value";

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void secretKvRecordValueIsMaskedTest() {
        ApplicationKvRecord record = jsonTransformer.parse(
                "{\"key\":\"apiToken\",\"value\":\"" + SECRET + "\",\"secret\":true}", ApplicationKvRecord.class);
        assertEquals(SECRET, record.getValue());
        assertFalse(record.toString().contains(SECRET), record.toString());
        assertTrue(record.toString().contains("value=****"), record.toString());
        assertTrue(record.toString().contains("key=apiToken"), record.toString());

        record.setSecret(false);
        assertTrue(record.toString().contains("value=" + SECRET), record.toString());
    }

    @Test
    void secretAddKvRecordRequestValueIsMaskedTest() {
        Map<String, Object> value = new LinkedHashMap<>();
        value.put("password", SECRET);
        AddApplicationKvRecordRequest request = new AddApplicationKvRecordRequest();
        request.setKey("auth");
        request.setValue(value);
        request.setSecret(true);

        assertFalse(request.toString().contains(SECRET), request.toString());
        assertEquals("{\"key\":\"auth\",\"value\":{\"password\":\"" + SECRET + "\"},\"secret\":true}",
                jsonTransformer.convert(request));

        request.setSecret(null);
        assertTrue(request.toString().contains(SECRET), request.toString());
    }

    @Test
    void kvRecordPatchValueIsMaskedTest() {
        ApplicationKvRecordPatchRequest value = new ApplicationKvRecordPatchRequest();
        value.setOp(PatchOperation.REPLACE);
        value.setPath("/value");
        value.setValue(SECRET);
        assertFalse(value.toString().contains(SECRET), value.toString());
        assertEquals("ApplicationKvRecordPatchRequest(op=REPLACE, path=/value, value=****)", value.toString());
        assertEquals("{\"value\":\"" + SECRET + "\",\"path\":\"/value\",\"op\":\"replace\"}", jsonTransformer.convert(value));

        ApplicationKvRecordPatchRequest ttl = new ApplicationKvRecordPatchRequest();
        ttl.setOp(PatchOperation.REPLACE);
        ttl.setPath("/ttl");
        assertEquals("ApplicationKvRecordPatchRequest(op=REPLACE, path=/ttl, value=null)", ttl.toString());
        assertEquals("{\"value\":null,\"path\":\"/ttl\",\"op\":\"replace\"}", jsonTransformer.convert(ttl));
    }

    @Test
    void integrationLoginCredentialsAreHiddenTest() {
        Map<String, Object> credentials = new LinkedHashMap<>();
        credentials.put("login", "user");
        credentials.put("apiKey", SECRET);
        IntegrationLoginRequest request = new IntegrationLoginRequest();
        request.setProjectId(1L);
        request.setCredentials(credentials);

        assertEquals("IntegrationLoginRequest(projectId=1, credentials=[login, apiKey])", request.toString());
        assertEquals("{\"projectId\":1,\"credentials\":{\"login\":\"user\",\"apiKey\":\"" + SECRET + "\"}}",
                jsonTransformer.convert(request));
    }

    @Test
    void integrationSettingsConfigIsHiddenTest() {
        Map<String, Object> config = new LinkedHashMap<>();
        config.put("token", SECRET);
        UpdateIntegrationSettingsRequest request = new UpdateIntegrationSettingsRequest();
        request.setProjectId(1L);
        request.setConfig(config);

        assertEquals("UpdateIntegrationSettingsRequest(projectId=1, config=[token])", request.toString());
        assertEquals("{\"projectId\":1,\"config\":{\"token\":\"" + SECRET + "\"}}", jsonTransformer.convert(request));
    }

    @Test
    void kvRecordValueShapesRoundTripTest() {
        String[] values = {"\"text\"", "42", "1.5", "true", "[1,\"a\",false]", "{\"theme\":\"dark\",\"size\":{\"w\":2}}"};
        Object[] expected = {"text", 42, 1.5, true, Arrays.asList(1, "a", false),
                Collections.singletonMap("theme", "dark")};
        for (int i = 0; i < values.length; i++) {
            String json = "{\"key\":\"k\",\"value\":" + values[i] + "}";
            ApplicationKvRecord record = jsonTransformer.parse(json, ApplicationKvRecord.class);
            if (record.getValue() instanceof Map) {
                assertEquals("dark", ((Map<?, ?>) record.getValue()).get("theme"));
                assertTrue(((Map<?, ?>) record.getValue()).get("size") instanceof Map);
            } else {
                assertEquals(expected[i], record.getValue(), values[i]);
            }
            if (record.getValue() instanceof List) {
                assertEquals(3, ((List<?>) record.getValue()).size());
            }

            AddApplicationKvRecordRequest request = new AddApplicationKvRecordRequest();
            request.setKey("k");
            request.setValue(record.getValue());
            assertEquals(json, jsonTransformer.convert(request));
        }
    }
}
