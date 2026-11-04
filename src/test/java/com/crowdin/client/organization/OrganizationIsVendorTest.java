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

    @Test
    void setIsVendorKeepsRawInSyncTest() {
        Organization organization = new Organization();
        organization.setIsVendor(true);
        assertState(organization, Boolean.TRUE, "true", "{\"isVendor\":true}");
        organization.setIsVendor(false);
        assertState(organization, Boolean.FALSE, "false", "{\"isVendor\":false}");
        organization.setIsVendor(null);
        assertState(organization, null, null, "{}");
    }

    @Test
    void setIsVendorRawRecomputesBooleanTest() {
        Organization organization = new Organization();
        organization.setIsVendorRaw("true");
        assertState(organization, Boolean.TRUE, "true", "{\"isVendor\":true}");
        organization.setIsVendorRaw("false");
        assertState(organization, Boolean.FALSE, "false", "{\"isVendor\":false}");
        organization.setIsVendorRaw("TRUE");
        assertState(organization, Boolean.TRUE, "TRUE", "{\"isVendor\":true}");
        organization.setIsVendorRaw("vendor");
        assertState(organization, null, "vendor", "{\"isVendor\":\"vendor\"}");
        organization.setIsVendorRaw(null);
        assertState(organization, null, null, "{}");
    }

    @Test
    void lastSetterWinsInBothOrdersTest() {
        Organization booleanLast = new Organization();
        booleanLast.setIsVendorRaw("vendor");
        booleanLast.setIsVendor(false);
        assertState(booleanLast, Boolean.FALSE, "false", "{\"isVendor\":false}");

        Organization rawLast = new Organization();
        rawLast.setIsVendor(true);
        rawLast.setIsVendorRaw("false");
        assertState(rawLast, Boolean.FALSE, "false", "{\"isVendor\":false}");

        Organization rawUnknownLast = new Organization();
        rawUnknownLast.setIsVendor(true);
        rawUnknownLast.setIsVendorRaw("vendor");
        assertState(rawUnknownLast, null, "vendor", "{\"isVendor\":\"vendor\"}");

        Organization clearedByBoolean = new Organization();
        clearedByBoolean.setIsVendorRaw("vendor");
        clearedByBoolean.setIsVendor(null);
        assertState(clearedByBoolean, null, null, "{}");
    }

    @Test
    void settersAfterDeserializationStayConsistentTest() {
        Organization parsedFalse = jsonTransformer.parse("{\"isVendor\":false}", Organization.class);
        parsedFalse.setIsVendor(true);
        assertState(parsedFalse, Boolean.TRUE, "true", "{\"isVendor\":true}");

        Organization parsedTrue = jsonTransformer.parse("{\"isVendor\":true}", Organization.class);
        parsedTrue.setIsVendorRaw("false");
        assertState(parsedTrue, Boolean.FALSE, "false", "{\"isVendor\":false}");

        Organization parsedString = jsonTransformer.parse("{\"isVendor\":\"vendor\"}", Organization.class);
        parsedString.setIsVendor(false);
        assertState(parsedString, Boolean.FALSE, "false", "{\"isVendor\":false}");
    }

    @Test
    void setterAndJsonReaderAgreeTest() {
        for (String raw : new String[]{"true", "false", "TRUE", "False", "vendor", "1", "", " true "}) {
            Organization viaSetter = new Organization();
            viaSetter.setIsVendorRaw(raw);
            Organization viaJson = jsonTransformer.parse("{\"isVendor\":\"" + raw + "\"}", Organization.class);
            assertEquals(viaJson.getIsVendor(), viaSetter.getIsVendor(), raw);
            assertEquals(viaJson.getIsVendorRaw(), viaSetter.getIsVendorRaw(), raw);
            assertEquals(jsonTransformer.convert(viaJson), jsonTransformer.convert(viaSetter), raw);
        }
    }

    private void assertState(Organization organization, Boolean expected, String expectedRaw, String expectedJson) {
        assertEquals(expected, organization.getIsVendor());
        assertEquals(expectedRaw, organization.getIsVendorRaw());
        String json = jsonTransformer.convert(organization);
        assertEquals(expectedJson, json);
        assertEquals(expectedJson.contains("isVendor") ? 1 : 0, json.split("\"isVendor\"", -1).length - 1);
        if (expected != null) {
            assertEquals(expected.toString(), expectedRaw.toLowerCase());
        }
    }

    private void assertIsVendor(String jsonValue, Boolean expected, String expectedRaw) {
        Organization organization = jsonTransformer.parse("{\"id\":1,\"isVendor\":" + jsonValue + "}", Organization.class);
        assertEquals(expected, organization.getIsVendor(), jsonValue);
        assertEquals(expectedRaw, organization.getIsVendorRaw(), jsonValue);
        assertEquals(Long.valueOf(1), organization.getId());
    }
}
