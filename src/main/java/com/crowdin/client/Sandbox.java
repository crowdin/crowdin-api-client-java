package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.ClientConfig;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.users.model.User;
import lombok.var;

import java.io.FileNotFoundException;

public class Sandbox {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        if (args.length > 0) {
            try {
                Credentials credentials = new Credentials(args[0], "oliynyk");
                var client = new Client(credentials, ClientConfig.builder().httpTimeoutMs(5000).build());
                ResponseObject<User> user = client.getUsersApi().getAuthenticatedUser();
                System.out.println(user.getData());
            } catch (HttpException e) {
                System.out.println(e.getError());
            } catch (HttpBadRequestException e) {
                System.out.println(e.getErrors());
            }
        }
    }
}
