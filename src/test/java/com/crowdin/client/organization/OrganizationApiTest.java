package com.crowdin.client.organization;

import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.organization.model.AuthMethod;
import com.crowdin.client.organization.model.CustomSpellchecker;
import com.crowdin.client.organization.model.ExternalQaCheck;
import com.crowdin.client.organization.model.Organization;
import com.crowdin.client.organization.model.OrganizationAuthSettings;
import com.crowdin.client.organization.model.OrganizationResponseObject;
import com.crowdin.client.organization.model.PublicProjectsView;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizationApiTest extends TestClient {

    private final Long projectId = 8L;
    private final Long customSpellcheckerId = 2L;
    private final Long externalQaCheckId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("limit", 25);
        pageParams.put("offset", 0);

        Map<String, Object> qaParams = new HashMap<>(pageParams);
        qaParams.put("projectId", projectId);

        return Arrays.asList(
                RequestMock.build(this.url + "/organization", HttpGet.METHOD_NAME, "api/organization/organization.json"),
                RequestMock.build(this.url + "/organization/auth-settings", HttpGet.METHOD_NAME, "api/organization/authSettings.json"),
                RequestMock.build(this.url + "/custom-spellcheckers", HttpGet.METHOD_NAME, "api/organization/listCustomSpellcheckers.json", pageParams),
                RequestMock.build(this.url + "/custom-spellcheckers/" + customSpellcheckerId, HttpGet.METHOD_NAME, "api/organization/customSpellchecker.json"),
                RequestMock.build(this.url + "/external-qa-checks", HttpGet.METHOD_NAME, "api/organization/listExternalQaChecks.json", qaParams)
        );
    }

    @Test
    public void getOrganizationTest() {
        ResponseObject<Organization> response = this.getOrganizationApi().getOrganization();
        Organization organization = response.getData();
        assertEquals(1L, organization.getId());
        assertEquals("crowdin", organization.getDomain());
        assertEquals("Crowdin", organization.getName());
        assertNull(organization.getLogo());
        assertEquals("", organization.getDefaultLogo());
        assertNull(organization.getDescription());
        assertEquals("Internal", organization.getInternalDescription());
        assertNull(organization.getCname());
        assertFalse(organization.getIsVendor());
        assertEquals(PublicProjectsView.GRID, organization.getDefaultPublicProjectsView());
        assertEquals("Team+", organization.getPlan().getName());
        assertEquals(7500000L, organization.getPlan().getWordsLimit());
        assertEquals(25L, organization.getPlan().getManagersLimit());
        assertEquals(1, organization.getDefaults().size());
        assertEquals("editorView", organization.getDefaults().get(0).getName());
        assertTrue(organization.getDefaults().get(0).getIsLocked());
        assertEquals("side-by-side", organization.getDefaults().get(0).getDefaultValue());
    }

    @Test
    public void organizationIsVendorAsStringTest() {
        String json = new BufferedReader(new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream("api/organization/organizationStringIsVendor.json")))
                .lines().collect(Collectors.joining("\n"));
        OrganizationResponseObject response = new JacksonJsonTransformer().parse(json, OrganizationResponseObject.class);
        assertTrue(response.getData().getIsVendor());
    }

    @Test
    public void getOrganizationAuthSettingsTest() {
        ResponseObject<OrganizationAuthSettings> response = this.getOrganizationApi().getOrganizationAuthSettings();
        OrganizationAuthSettings settings = response.getData();
        assertTrue(settings.getAllowSignUp());
        assertFalse(settings.getTwoFactorAuthentication());
        AuthMethod authMethod = settings.getAuthMethods().get(0);
        assertEquals("Password", authMethod.getName());
        assertTrue(authMethod.getIsEnabled());
        assertTrue(authMethod.getIsDefault());
    }

    @Test
    public void listCustomSpellcheckersTest() {
        ResponseList<CustomSpellchecker> response = this.getOrganizationApi().listCustomSpellcheckers(25, 0);
        assertEquals(1, response.getData().size());
        assertCustomSpellchecker(response.getData().get(0).getData());
    }

    @Test
    public void getCustomSpellcheckerTest() {
        ResponseObject<CustomSpellchecker> response = this.getOrganizationApi().getCustomSpellchecker(customSpellcheckerId);
        assertCustomSpellchecker(response.getData());
    }

    @Test
    public void listExternalQaChecksTest() {
        ResponseList<ExternalQaCheck> response = this.getOrganizationApi().listExternalQaChecks(projectId, 25, 0);
        assertEquals(1, response.getData().size());
        assertExternalQaCheck(response.getData().get(0).getData());
    }

    /**
     * The core DateDeserializer parses timestamps in the JVM default time zone (offset ignored).
     */
    private static Date localDate(String dateTime) {
        return Date.from(LocalDateTime.parse(dateTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    private void assertCustomSpellchecker(CustomSpellchecker spellchecker) {
        assertEquals(customSpellcheckerId, spellchecker.getId());
        assertEquals("Custom Spellchecker", spellchecker.getName());
        assertEquals("app-identifier", spellchecker.getConfig().getIdentifier());
        assertEquals("app-module-key", spellchecker.getConfig().getKey());
        assertTrue(spellchecker.getConfig().getRealTimeCheckEnabled());
        assertEquals(Arrays.asList("en", "es"), spellchecker.getConfig().getEnabledLanguageIds());
        assertEquals(localDate("2019-09-23T07:19:47"), spellchecker.getCreatedAt());
        assertNull(spellchecker.getUpdatedAt());
    }

    private void assertExternalQaCheck(ExternalQaCheck check) {
        assertEquals(externalQaCheckId, check.getId());
        assertEquals("Test QA check", check.getName());
        assertEquals("Do some QA checks", check.getDescription());
        assertEquals("app-identifier", check.getConfig().get("identifier"));
        assertEquals(true, check.getConfig().get("enabled"));
        assertEquals(localDate("2024-09-19T14:14:00"), check.getCreatedAt());
        assertEquals(localDate("2024-09-19T14:14:00"), check.getUpdatedAt());
    }
}
