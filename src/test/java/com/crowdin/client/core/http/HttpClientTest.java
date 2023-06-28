package com.crowdin.client.core.http;

import com.crowdin.client.framework.TestHttpClient;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTest {

    @Test
    public void appendUrlParamsTest() {
        String url = "test.com";
        Map<String, Optional<Object>> urlParams = new LinkedHashMap<>();
        urlParams.put("a", Optional.empty());
        urlParams.put("b", Optional.of(4));
        urlParams.put("c", Optional.of("tbx"));
        HttpClient client = new TestHttpClient();
        String urlWithParams = client.appendUrlParams(url, urlParams);
        assertEquals(urlWithParams, url + "?b=" + 4 + "&c=" + "tbx");
    }

    @Test
    public void appendUrlParamsTest_noParams() {
        String url = "test.com";
        Map<String, Optional<Object>> urlParams = new LinkedHashMap<>();
        HttpClient client = new TestHttpClient();
        String urlWithParams = client.appendUrlParams(url, urlParams);
        assertEquals(urlWithParams, url);
    }
}
