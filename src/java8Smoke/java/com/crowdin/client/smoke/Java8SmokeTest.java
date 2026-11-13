package com.crowdin.client.smoke;

import com.crowdin.client.Client;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import com.crowdin.client.projectsgroups.model.Type;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Runs the built library jar on a Java 8 runtime against a local stub server, so a dependency
 * or JDK API that needs a newer Java fails here instead of in users' applications.
 */
public class Java8SmokeTest {

    private static final String PROJECT_JSON = "{\"data\":{"
            + "\"id\":9,\"type\":0,\"name\":\"Knowledge Base\",\"identifier\":\"knowledge-base\","
            + "\"sourceLanguageId\":\"es\",\"targetLanguageIds\":[\"uk\"],\"visibility\":\"private\","
            + "\"createdAt\":\"2023-09-20T11:34:40+00:00\",\"inContext\":false}}";

    private static final String NOT_FOUND_JSON = "{\"error\":{\"code\":404,\"message\":\"Project Not Found\"}}";

    public static void main(String[] args) throws Exception {
        String javaVersion = System.getProperty("java.specification.version");
        check("1.8".equals(javaVersion), "expected a Java 8 runtime, got " + javaVersion);

        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/api/v2/projects/", Java8SmokeTest::handle);
        server.start();
        try {
            String baseUrl = "http://127.0.0.1:" + server.getAddress().getPort();
            Client client = new Client(new Credentials("smoke-token", null, baseUrl));

            ResponseObject<? extends Project> response = client.getProjectsGroupsApi().getProject(9L);
            Project project = response.getData();
            check(project instanceof ProjectSettings, "expected ProjectSettings, got " + project.getClass());
            check(project.getId() == 9L, "unexpected project id " + project.getId());
            check(project.getType() == Type.FILES_BASED, "unexpected project type " + project.getType());
            check("Knowledge Base".equals(project.getName()), "unexpected project name " + project.getName());
            check(project.getCreatedAt() != null, "createdAt was not parsed");

            try {
                client.getProjectsGroupsApi().getProject(404L);
                check(false, "expected HttpException for a 404 response");
            } catch (HttpException e) {
                check("Project Not Found".equals(e.getError().getMessage()), "unexpected error " + e.getError());
            }
        } finally {
            server.stop(0);
        }

        System.out.println("Java " + System.getProperty("java.version") + " smoke test passed");
    }

    private static void handle(HttpExchange exchange) throws IOException {
        boolean authorized = "Bearer smoke-token".equals(exchange.getRequestHeaders().getFirst("Authorization"));
        boolean found = exchange.getRequestURI().getPath().endsWith("/projects/9");
        int status = !authorized ? 401 : found ? 200 : 404;
        byte[] body = (status == 200 ? PROJECT_JSON : NOT_FOUND_JSON).getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, body.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(body);
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException("Java 8 smoke test failed: " + message);
        }
    }
}
