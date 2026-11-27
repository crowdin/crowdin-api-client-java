package com.crowdin.client.core.http;

import com.crowdin.client.framework.TestHttpClient;
import org.junit.jupiter.api.Test;

import java.util.Date;
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

    @Test
    public void appendUrlParamsTest_dateIsFormattedAsIso8601() {
        String url = "test.com";
        Map<String, Optional<Object>> urlParams = new LinkedHashMap<>();
        urlParams.put("lastSeenFrom", Optional.of(new Date(1571831042000L)));
        HttpClient client = new TestHttpClient();
        String urlWithParams = client.appendUrlParams(url, urlParams);
        assertEquals(url + "?lastSeenFrom=2019-10-23T11:44:02Z", urlWithParams);
    }

    @Test
    public void appendUrlParamsTest_freeTextParamsAreEncoded() {
        String url = "test.com";
        Map<String, Optional<Object>> urlParams = new LinkedHashMap<>();
        urlParams.put("search", Optional.of("Q&A #1"));
        urlParams.put("filter", Optional.of("a+b=100%"));
        urlParams.put("croql", Optional.of("text contains \"x\""));
        urlParams.put("orderBy", Optional.of("id%20desc"));
        HttpClient client = new TestHttpClient();
        String urlWithParams = client.appendUrlParams(url, urlParams);
        assertEquals(url + "?search=Q%26A%20%231&filter=a%2Bb%3D100%25&croql=text%20contains%20%22x%22&orderBy=id%20desc", urlWithParams);
    }
}
