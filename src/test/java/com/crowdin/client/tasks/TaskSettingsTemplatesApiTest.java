package com.crowdin.client.tasks;

import com.crowdin.client.core.model.PatchOperation;
import com.crowdin.client.core.model.PatchRequest;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.framework.RequestMock;
import com.crowdin.client.framework.TestClient;
import com.crowdin.client.tasks.model.AddTaskSettingsTemplateRequest;
import com.crowdin.client.tasks.model.LanguageReference;
import com.crowdin.client.tasks.model.TaskSettingsTemplate;
import com.crowdin.client.tasks.model.TaskSettingsTemplateConfig;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSettingsTemplatesApiTest extends TestClient {
    private final Long projectId = 1L;
    private final Long taskSettingsTemplateId = 1L;

    @Override
    public List<RequestMock> getMocks() {
        return Arrays.asList(
                // LIST
                RequestMock.build(
                        formUrl_taskSettingsTemplates(projectId),
                        HttpGet.METHOD_NAME,
                        "api/tasks/settings-templates/listTaskSettingsTemplatesResponse.json",
                        new HashMap<String, Integer>() {{
                            put("limit", 20);
                            put("offset", 10);
                        }}
                ),

                // ADD
                RequestMock.build(
                        formUrl_taskSettingsTemplates(projectId),
                        HttpPost.METHOD_NAME,
                        "api/tasks/settings-templates/addTaskSettingsTemplateRequest.json",
                        "api/tasks/settings-templates/commonResponses_single.json"
                ),

                // GET
                RequestMock.build(
                        formUrl_taskSettingsTemplateId(projectId, taskSettingsTemplateId),
                        HttpGet.METHOD_NAME,
                        "api/tasks/settings-templates/commonResponses_single.json"
                ),

                // DELETE
                RequestMock.build(
                        formUrl_taskSettingsTemplateId(projectId, taskSettingsTemplateId),
                        HttpDelete.METHOD_NAME
                ),

                // PATCH
                RequestMock.build(
                        formUrl_taskSettingsTemplateId(projectId, taskSettingsTemplateId),
                        HttpPatch.METHOD_NAME,
                        "api/tasks/settings-templates/editTaskSettingsTemplateRequest.json",
                        "api/tasks/settings-templates/commonResponses_single.json"
                )
        );
    }

    //<editor-fold desc="Form Url methods for mocks">
    private String formUrl_taskSettingsTemplates(Long projectId) {
        return this.url + "/projects/" + projectId + "/tasks/settings-templates";
    }

    private String formUrl_taskSettingsTemplateId(Long projectId, Long taskSettingsTemplateId) {
        return this.url + "/projects/" + projectId + "/tasks/settings-templates/" + taskSettingsTemplateId;
    }
    //</editor-fold>

    @Test
    public void listTaskSettingsTemplates() {
        ResponseList<TaskSettingsTemplate> response = this.getTasksApi().listTaskSettingsTemplates(projectId, 20, 10);
        assertTaskSettingsTemplate(response.getData().get(0).getData());
    }

    @Test
    public void addTaskSettingsTemplate() {
        AddTaskSettingsTemplateRequest request = new AddTaskSettingsTemplateRequest() {{
            setName("Perfect name");
            setConfig(new TaskSettingsTemplateConfig() {{
                setLanguages(Collections.singletonList(new LanguageReference() {{
                    setLanguageId("uk");
                    setUserIds(Arrays.asList(1, 2, 3));
                    setTeamIds(Arrays.asList(4, 5, 6));
                }}));
            }});
        }};

        ResponseObject<TaskSettingsTemplate> response = this.getTasksApi().addTaskSettingsTemplate(projectId, request);
        assertTaskSettingsTemplate(response.getData());
    }

    @Test
    public void getTaskSettingsTemplate() {
        ResponseObject<TaskSettingsTemplate> response = this.getTasksApi().getTaskSettingsTemplate(projectId, taskSettingsTemplateId);
        assertTaskSettingsTemplate(response.getData());
    }

    @Test
    public void deleteTaskSettingsTemplate() {
        this.getTasksApi().deleteTaskSettingsTemplate(projectId, taskSettingsTemplateId);
    }

    @Test
    public void editTaskSettingsTemplate() {
        List<PatchRequest> request = new ArrayList<PatchRequest>() {{
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/name");
                setValue("New name");
            }});
            add(new PatchRequest() {{
                setOp(PatchOperation.REPLACE);
                setPath("/config");
                setValue(new TaskSettingsTemplateConfig() {{
                    setLanguages(Collections.singletonList(new LanguageReference() {{
                        setLanguageId("uk");
                        setUserIds(Arrays.asList(1, 2, 3));
                        setTeamIds(Arrays.asList(4, 5, 6));
                    }}));
                }});
            }});
        }};

        ResponseObject<TaskSettingsTemplate> response = this.getTasksApi().editTaskSettingsTemplate(projectId, taskSettingsTemplateId, request);
        assertTaskSettingsTemplate(response.getData());
    }

    @SneakyThrows
    private static void assertTaskSettingsTemplate(TaskSettingsTemplate template) {
        assertNotNull(template);

        assertEquals("Default template", template.getName());

        assertNotNull(template.getConfig());
        assertNotNull(template.getConfig().getLanguages());
        assertFalse(template.getConfig().getLanguages().isEmpty());

        LanguageReference languageReference = template.getConfig().getLanguages().get(0);
        assertEquals("uk", languageReference.getLanguageId());

        assertNotNull(languageReference.getUserIds());
        assertEquals(Arrays.asList(1, 2, 3), languageReference.getUserIds());

        assertNotNull(languageReference.getTeamIds());
        assertEquals(Arrays.asList(4, 5, 6), languageReference.getTeamIds());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        assertEquals(sdf.parse("2019-09-23T11:26:54+00:00"), template.getCreatedAt());
        assertEquals(sdf.parse("2019-09-23T11:26:54+00:00"), template.getUpdatedAt());
    }
}
