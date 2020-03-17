package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.projectsgroups.model.AddProjectRequest;
import com.crowdin.client.projectsgroups.model.Project;
import lombok.var;

import java.util.UUID;

public class Sandbox {

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Credentials credentials = new Credentials(args[0], "oliynyk");
                var client = new Client(credentials);
                ResponseList<Project> projects = client.getProjectsGroupsApi().listProjects(null, null, null, null);
                AddProjectRequest request = new AddProjectRequest();
                request.setName("Test project " + UUID.randomUUID().toString());
                request.setSourceLanguageId("en");
                request.setTemplateId(1);
                ResponseObject<Project> projectResponseObject = client.getProjectsGroupsApi().addProject(request);
                System.out.println(projectResponseObject);
            } catch (HttpException e) {
                System.out.println(e.getError());
            } catch (HttpBadRequestException e) {
                System.out.println(e.getErrors());
            }
        }
    }
}
