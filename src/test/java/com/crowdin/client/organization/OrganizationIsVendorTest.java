package com.crowdin.client.organization;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.organization.model.Organization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The API documents {@code isVendor} as a string while its example is a boolean; both are accepted and the raw value
 * is always kept.
 */
class OrganizationIsVendorTest {

    private final JacksonJsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    void booleanRepresentationTest() {
        assertIsVendor("true", Boolean.TRUE, "true");
        assertIsVendor("false", Boolean.FALSE, "false");
    }

    @Test
    void stringRepresentationTest() {
        assertIsVendor("\"true\"", Boolean.TRUE, "true");
        assertIsVendor("\"false\"", Boolean.FALSE, "false");
        assertIsVendor("\"TRUE\"", Boolean.TRUE, "TRUE");
    }

    @Test
    void unrecognizedValuesAreKeptRawTest() {
        assertIsVendor("\"1\"", null, "1");
        assertIsVendor("\"vendor\"", null, "vendor");
        assertIsVendor("\"\"", null, "");
        assertIsVendor("1", null, "1");
    }

    @Test
    void nullAndMissingTest() {
        assertIsVendor("null", null, null);
        Organization missing = jsonTransformer.parse("{\"id\":1}", Organization.class);
        assertNull(missing.getIsVendor());
        assertNull(missing.getIsVendorRaw());
    }

    @Test
    void serializationTest() {
        Organization organization = new Organization();
        assertEquals("{}", jsonTransformer.convert(organization));
        organization.setIsVendor(true);
        assertEquals("{\"isVendor\":true}", jsonTransformer.convert(organization));
        organization.setIsVendor(null);
        organization.setIsVendorRaw("vendor");
        assertEquals("{\"isVendor\":\"vendor\"}", jsonTransformer.convert(organization));
    }

    private void assertIsVendor(String jsonValue, Boolean expected, String expectedRaw) {
        Organization organization = jsonTransformer.parse("{\"id\":1,\"isVendor\":" + jsonValue + "}", Organization.class);
        assertEquals(expected, organization.getIsVendor(), jsonValue);
        assertEquals(expectedRaw, organization.getIsVendorRaw(), jsonValue);
        assertEquals(Long.valueOf(1), organization.getId());
    }
}
