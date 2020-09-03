package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.projectsgroups.model.ProjectSettings;
import com.crowdin.client.users.model.User;
import lombok.var;

import java.io.FileNotFoundException;

public class Sandbox {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        new Integer(null);

        if (true) return;

        Credentials credentials = new Credentials("1af38c02dd24a61fa721cc77e099ff9508f8e58b8417ba040b18507e5f5cbffe4ddcdcb84e5610c7", null);
        Client client = new Client(credentials);

        ProjectSettings project = (ProjectSettings) client.getProjectsGroupsApi()
            .getProject(383090L)
            .getData();

        System.out.println("project = " + project);

        if (true) return;

//        if (args.length > 0) {
//            try {
//                Credentialzs credentials = new Credentials(args[0], "oliynyk");
//                var client = new Client(credentials);
//                ResponseObject<User> user = client.getUsersApi().getAuthenticatedUser();
//                System.out.println(user.getData());
//            } catch (HttpException e) {
//                System.out.println(e.getError());
//            } catch (HttpBadRequestException e) {
//                System.out.println(e.getErrors());
//            }
//        }
    }
}
