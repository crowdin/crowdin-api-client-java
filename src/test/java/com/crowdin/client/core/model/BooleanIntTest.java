package com.crowdin.client.core.model;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.labels.model.Label;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BooleanIntTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void fromNullReturnsNullTest() {
        assertNull(BooleanInt.from(null));
    }

    @Test
    void fromNumericValuesTest() {
        assertEquals(BooleanInt.TRUE, BooleanInt.from("1"));
        assertEquals(BooleanInt.FALSE, BooleanInt.from("0"));
    }

    @Test
    void fromBooleanValuesTest() {
        assertEquals(BooleanInt.TRUE, BooleanInt.from("true"));
        assertEquals(BooleanInt.TRUE, BooleanInt.from("TRUE"));
        assertEquals(BooleanInt.FALSE, BooleanInt.from("false"));
        assertEquals(BooleanInt.FALSE, BooleanInt.from("FALSE"));
    }

    @Test
    void fromOtherNonNullValuesIsFalseTest() {
        for (String value : new String[]{"", "2", "-1", "1.0", "yes"}) {
            assertEquals(BooleanInt.FALSE, BooleanInt.from(value), value);
        }
    }

    @Test
    void fromIntTest() {
        assertEquals(BooleanInt.TRUE, BooleanInt.fromInt(1));
        assertEquals(BooleanInt.FALSE, BooleanInt.fromInt(0));
        assertNull(BooleanInt.fromInt(null));
    }

    @Test
    void jsonDeserializationTest() {
        assertEquals(BooleanInt.TRUE, isSystem("1"));
        assertEquals(BooleanInt.FALSE, isSystem("0"));
        assertEquals(BooleanInt.TRUE, isSystem("\"1\""));
        assertEquals(BooleanInt.FALSE, isSystem("\"0\""));
        assertEquals(BooleanInt.TRUE, isSystem("true"));
        assertEquals(BooleanInt.FALSE, isSystem("false"));
        assertNull(isSystem("null"));
        assertNull(jsonTransformer.parse("{\"id\":1}", Label.class).getIsSystem());
    }

    @Test
    void jsonSerializationTest() {
        assertEquals("1", jsonTransformer.convert(BooleanInt.TRUE));
        assertEquals("0", jsonTransformer.convert(BooleanInt.FALSE));
    }

    private BooleanInt isSystem(String jsonValue) {
        return jsonTransformer.parse("{\"id\":1,\"isSystem\":" + jsonValue + "}", Label.class).getIsSystem();
    }
}
