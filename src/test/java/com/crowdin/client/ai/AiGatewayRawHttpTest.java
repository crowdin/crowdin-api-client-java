package com.crowdin.client.ai;

import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercises the AI gateway methods through the real {@code ApacheHttpClient} and {@code JacksonJsonTransformer}
 * against a local HTTP server.
 */
class AiGatewayRawHttpTest {

    private static final Map<String, String[]> RESPONSES = new LinkedHashMap<>();

    static {
        RESPONSES.put("object", new String[]{"200", "{\"id\":\"chatcmpl-1\",\"choices\":[{\"index\":0}]}"});
        RESPONSES.put("array", new String[]{"200", "[{\"id\":\"model-a\"},{\"id\":\"model-b\"}]"});
        RESPONSES.put("string", new String[]{"200", "\"plain\""});
        RESPONSES.put("integer", new String[]{"200", "42"});
        RESPONSES.put("decimal", new String[]{"200", "0.75"});
        RESPONSES.put("boolean", new String[]{"200", "true"});
        RESPONSES.put("jsonnull", new String[]{"200", "null"});
        RESPONSES.put("empty", new String[]{"200", ""});
        RESPONSES.put("blank", new String[]{"200", "  \n"});
        RESPONSES.put("text", new String[]{"200", "not json"});
        RESPONSES.put("wrapped", new String[]{"200", "{\"data\":{\"id\":\"x\"}}"});
        RESPONSES.put("error", new String[]{"400", "{\"error\":{\"code\":400,\"message\":\"provider rejected the request\"}}"});
        RESPONSES.put("nocontent", new String[]{"204", null});
    }

    private final List<String[]> requests = new CopyOnWriteArrayList<>();
    private HttpServer server;
    private AIApi api;

    @BeforeEach
    void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            String body = read(exchange.getRequestBody());
            requests.add(new String[]{exchange.getRequestMethod(), exchange.getRequestURI().toString(), body,
                    exchange.getRequestHeaders().getFirst("Authorization")});
            String path = exchange.getRequestURI().getPath();
            String[] response = RESPONSES.get(path.substring(path.lastIndexOf('/') + 1));
            int status = Integer.parseInt(response[0]);
            if (response[1] == null) {
                exchange.sendResponseHeaders(status, -1);
            } else {
                byte[] bytes = response[1].getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(status, bytes.length == 0 ? -1 : bytes.length);
                if (bytes.length > 0) {
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                }
            }
            exchange.close();
        });
        server.start();
        api = new AIApi(new Credentials("test-token", null, "http://127.0.0.1:" + server.getAddress().getPort()));
    }

    @AfterEach
    void stopServer() {
        server.stop(0);
    }

    @Test
    @SuppressWarnings("unchecked")
    void jsonResponseTypesTest() {
        Map<String, Object> object = (Map<String, Object>) api.aiGatewayGetRaw(7L, 3L, "v1/object");
        assertEquals("chatcmpl-1", object.get("id"));
        assertEquals(1, ((List<Object>) object.get("choices")).size());

        List<Object> array = (List<Object>) api.aiGatewayGetRaw(7L, 3L, "v1/array");
        assertEquals(2, array.size());
        assertEquals("model-b", ((Map<String, Object>) array.get(1)).get("id"));

        assertEquals("plain", api.aiGatewayGetRaw(7L, 3L, "v1/string"));
        assertEquals(42, ((Number) api.aiGatewayGetRaw(7L, 3L, "v1/integer")).intValue());
        assertEquals(0.75, ((Number) api.aiGatewayGetRaw(7L, 3L, "v1/decimal")).doubleValue());
        assertEquals(Boolean.TRUE, api.aiGatewayGetRaw(7L, 3L, "v1/boolean"));
        assertNull(api.aiGatewayGetRaw(7L, 3L, "v1/jsonnull"));
    }

    @Test
    void emptyBodyIsNullTest() {
        assertNull(api.aiGatewayGetRaw(7L, 3L, "v1/empty"));
        assertNull(api.aiGatewayDeleteRaw(null, 3L, "v1/empty"));
        assertNull(api.aiGatewayPostRaw(7L, 3L, "v1/blank", Collections.singletonMap("model", "m")));
    }

    @Test
    void nonJsonBodyFailsWithHttpExceptionTest() {
        HttpException e = assertThrows(HttpException.class, () -> api.aiGatewayGetRaw(7L, 3L, "v1/text"));
        assertEquals("not json", e.getHttpResponse());
        assertTrue(e.getError().getMessage().startsWith("AI gateway response is not valid JSON"), e.getError().getMessage());
    }

    @Test
    void errorStatusFailsWithHttpExceptionTest() {
        HttpException e = assertThrows(HttpException.class, () -> api.aiGatewayPostRaw(7L, 3L, "v1/error", Collections.singletonMap("model", "m")));
        assertEquals("provider rejected the request", e.getError().getMessage());
    }

    /**
     * Documented limitation: the default HTTP client cannot read a 204 (No Content) response for a typed result;
     * this is a transport behaviour shared by all SDK methods and is not changed here.
     */
    @Test
    void noContentResponseIsNotSupportedTest() {
        assertThrows(RuntimeException.class, () -> api.aiGatewayDeleteRaw(7L, 3L, "v1/nocontent"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void requestsUseDocumentedMethodsAndPathsTest() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("model", "gpt");
        body.put("messages", Collections.singletonList(Collections.singletonMap("role", "user")));

        api.aiGatewayGetRaw(7L, 3L, "v1/object");
        api.aiGatewayPostRaw(7L, 3L, "v1/object", body);
        api.aiGatewayPutRaw(7L, 3L, "v1/object", body);
        api.aiGatewayPatchRaw(null, 3L, "v1/object", body);
        api.aiGatewayDeleteRaw(null, 3L, "v1/object");

        List<String> expected = Arrays.asList(
                "GET /api/v2/users/7/ai/providers/3/gateway/v1/object",
                "POST /api/v2/users/7/ai/providers/3/gateway/v1/object",
                "PUT /api/v2/users/7/ai/providers/3/gateway/v1/object",
                "PATCH /api/v2/ai/providers/3/gateway/v1/object",
                "DELETE /api/v2/ai/providers/3/gateway/v1/object");
        assertEquals(5, requests.size());
        for (int i = 0; i < expected.size(); i++) {
            String[] request = requests.get(i);
            assertEquals(expected.get(i), request[0] + " " + request[1]);
            assertEquals("Bearer test-token", request[3]);
        }
        assertEquals("{\"model\":\"gpt\",\"messages\":[{\"role\":\"user\"}]}", requests.get(1)[2]);
        assertEquals("", requests.get(0)[2]);
    }

    @Test
    @SuppressWarnings("deprecation")
    void legacyGatewayMethodsAreUnchangedTest() {
        assertEquals("x", api.aiGatewayGet(7L, 3L, "v1/wrapped").getData().get("id"));
        assertNull(api.aiGatewayGet(7L, 3L, "v1/object").getData());
        api.aiGatewayDelete(7L, 3L, "v1/nocontent");
        assertThrows(HttpException.class, () -> api.aiGatewayGet(7L, 3L, "v1/array"));
    }

    private static String read(InputStream in) throws java.io.IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = in.read(buffer)) > 0) {
            out.write(buffer, 0, n);
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }
}
