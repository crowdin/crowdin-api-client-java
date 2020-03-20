package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.projectsgroups.model.Project;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import lombok.var;

import java.io.FileNotFoundException;

public class Sandbox {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            try {
                Credentials credentials = new Credentials(args[0], "oliynyk");
                var client = new Client(credentials);
                ResponseList<? extends Project> projects = client.getProjectsGroupsApi().listProjects(null, null, null, null);
                projects.getData().stream().map(ResponseObject::getData).forEach(p -> {
                    if (p instanceof ProjectSettings) {
                        System.out.println(((ProjectSettings) p).getLanguageMapping());
                    } else {
                        System.out.println("standard project object");
                    }
                });
            } catch (HttpException e) {
                System.out.println(e.getError());
            } catch (HttpBadRequestException e) {
                System.out.println(e.getErrors());
            }
        }
    }
}
