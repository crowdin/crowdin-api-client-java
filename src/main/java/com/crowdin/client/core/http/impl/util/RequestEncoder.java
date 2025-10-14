package com.crowdin.client.core.http.impl.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RequestEncoder {
    public static String encodeSpaces(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
