package com.crowdin.client.webhooks;

import com.crowdin.client.webhooks.model.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebhooksModelTest {

    @Test
    public void testEventConversion() {
        for (Event event : Event.values()) {
            String raw = (String) event.to(event);
            assertEquals(event, Event.from(raw));
        }
    }
}
