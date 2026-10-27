package com.crowdin.client.fields;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.fields.model.Config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("deprecation")
class ConfigTest {

    @Test
    void deprecatedGettersTruncateFractionalValuesTest() {
        assertEquals(1, minOf(1.5));
        assertEquals(-1, minOf(-1.5));
        assertEquals(0, minOf(0.999));
        assertEquals(0, minOf(-0.999));
        assertEquals(9, maxOf(9.9));
        assertEquals(-9, maxOf(-9.9));
        assertEquals(10, maxOf(10.0));
    }

    @Test
    void deprecatedGettersReturnIntegerBoundaryValuesTest() {
        assertEquals(Integer.MAX_VALUE, minOf((double) Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, minOf((double) Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, maxOf((double) Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE, maxOf((double) Integer.MIN_VALUE));
    }

    @Test
    void deprecatedGettersReturnNullForOutOfRangeValuesTest() {
        assertNull(minOf(Integer.MAX_VALUE + 1.0));
        assertNull(minOf(Integer.MIN_VALUE - 1.0));
        assertNull(minOf(Integer.MAX_VALUE + 0.5));
        assertNull(minOf(1e10));
        assertNull(maxOf(-1e10));
        assertNull(maxOf(Double.POSITIVE_INFINITY));
        assertNull(maxOf(Double.NEGATIVE_INFINITY));
        assertNull(maxOf(Double.NaN));
        assertNull(minOf(null));
        assertNull(maxOf(null));
    }

    @Test
    void deprecatedGettersOnDeserializedConfigTest() {
        Config config = new JacksonJsonTransformer().parse("{\"min\":3000000000,\"max\":-12.75}", Config.class);

        assertEquals(3.0E9, config.getMinValue());
        assertNull(config.getMin());
        assertEquals(-12.75, config.getMaxValue());
        assertEquals(-12, config.getMax());
    }

    @Test
    void deprecatedSettersSendNumbersTest() {
        Config config = new Config();
        config.setMin(Integer.MIN_VALUE);
        config.setMax(Integer.MAX_VALUE);

        assertEquals("{\"min\":-2.147483648E9,\"max\":2.147483647E9}", new JacksonJsonTransformer().convert(config));
        assertEquals(Integer.MIN_VALUE, config.getMin());
        assertEquals(Integer.MAX_VALUE, config.getMax());
    }

    private static Integer minOf(Double value) {
        Config config = new Config();
        config.setMinValue(value);
        return config.getMin();
    }

    private static Integer maxOf(Double value) {
        Config config = new Config();
        config.setMaxValue(value);
        return config.getMax();
    }
}
