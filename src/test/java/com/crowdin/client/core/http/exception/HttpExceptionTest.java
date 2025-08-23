package com.crowdin.client.core.http.exception;

import com.crowdin.client.core.http.exceptions.HttpException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpExceptionTest {

    @Test
    public void httpExceptionFromTest() {
        HttpException message = HttpException.fromMessage("Exception message!", null);
        assertEquals(message.getError().getMessage(), "Exception message!");
    }
}
