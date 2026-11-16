package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.glossaries.model.ConceptResponseObject;
import com.crowdin.client.glossaries.model.TermResponseObject;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectResponseObject;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import com.crowdin.client.sourcestrings.model.SourceStringResponseObject;
import com.crowdin.client.users.model.UserResponseObject;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The API sends some empty objects as {@code []}; the shared mapper must read them as {@code null}
 * for every Map and POJO field, without per-field annotations.
 */
public class EmptyArrayCoercionTest {

    private final JacksonJsonTransformer transformer = new JacksonJsonTransformer();

    @Test
    public void projectFieldsEmptyArray() {
        Project project = transformer.parse("{\"data\":{\"id\":1,\"fields\":[]}}", ProjectResponseObject.class).getData();

        assertEquals(1L, project.getId());
        assertNull(project.getFields());
    }

    @Test
    public void projectSettingsFieldsEmptyArray() {
        Project project = transformer.parse("{\"data\":{\"id\":1,\"languageMapping\":[],\"fields\":[]}}", ProjectResponseObject.class).getData();

        assertInstanceOf(ProjectSettings.class, project);
        assertNull(project.getFields());
    }

    @Test
    public void projectSettingsObjectsEmptyArray() {
        ProjectSettings settings = (ProjectSettings) transformer.parse("{\"data\":{\"id\":1,\"languageMapping\":[],"
            + "\"assignedTms\":[],\"tmPreTranslate\":[],\"mtPreTranslate\":[],\"aiPreTranslate\":[]}}", ProjectResponseObject.class).getData();

        assertNull(settings.getAssignedTms());
        assertNull(settings.getTmPreTranslate());
        assertNull(settings.getMtPreTranslate());
        assertNull(settings.getAiPreTranslate());
    }

    @Test
    public void sourceStringFieldsEmptyArray() {
        assertNull(transformer.parse("{\"data\":{\"id\":1,\"fields\":[]}}", SourceStringResponseObject.class).getData().getFields());
    }

    @Test
    public void conceptFieldsEmptyArray() {
        assertNull(transformer.parse("{\"data\":{\"id\":1,\"fields\":[]}}", ConceptResponseObject.class).getData().getFields());
    }

    @Test
    public void termFieldsEmptyArray() {
        assertNull(transformer.parse("{\"data\":{\"id\":1,\"fields\":[]}}", TermResponseObject.class).getData().getFields());
    }

    @Test
    public void userFieldsEmptyArray() {
        assertNull(transformer.parse("{\"data\":{\"id\":1,\"fields\":[]}}", UserResponseObject.class).getData().getFields());
    }

    @Test
    public void nestedObjectEmptyArray() {
        assertNull(transformer.parse("{\"data\":{\"id\":1,\"sourceLanguage\":[]}}", ProjectResponseObject.class).getData().getSourceLanguage());
    }

    @Test
    public void populatedMapStillParses() {
        Project project = transformer.parse("{\"data\":{\"id\":1,\"fields\":{\"slug\":\"value\"}}}", ProjectResponseObject.class).getData();

        assertEquals(Collections.singletonMap("slug", "value"), project.getFields());
    }

    @Test
    public void emptyArrayStaysEmptyListForListFields() {
        Project project = transformer.parse("{\"data\":{\"id\":1,\"targetLanguageIds\":[]}}", ProjectResponseObject.class).getData();

        assertTrue(project.getTargetLanguageIds().isEmpty());
    }

    @Test
    public void nonEmptyArrayForMapStillFails() {
        assertThrows(MismatchedInputException.class,
            () -> transformer.parse("{\"data\":{\"id\":1,\"fields\":[1]}}", SourceStringResponseObject.class));
    }
}
