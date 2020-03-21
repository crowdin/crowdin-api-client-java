package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.ResponseObject;
import com.crowdin.client.translations.model.BuildProjectTranslationRequest;
import com.crowdin.client.translations.model.ProjectBuild;
import lombok.var;

import java.io.FileNotFoundException;

public class Sandbox {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        if (args.length > 0) {
            try {
                Credentials credentials = new Credentials(args[0], "oliynyk");
                var client = new Client(credentials);
                BuildProjectTranslationRequest request = new BuildProjectTranslationRequest();
                ResponseObject<ProjectBuild> projectBuildResponseObject = client.getTranslationsApi().buildProjectTranslation(123L, request);
                System.out.println(projectBuildResponseObject.getData());
                Thread.sleep(1000);
                ResponseObject<ProjectBuild> buildStatus = client.getTranslationsApi().checkBuildStatus(123L, projectBuildResponseObject.getData().getId());
                System.out.println(buildStatus.getData());
                Thread.sleep(2000);
                ResponseObject<DownloadLink> downloadLinkResponseObject = client.getTranslationsApi().downloadProjectTranslations(123L, projectBuildResponseObject.getData().getId());
                System.out.println(downloadLinkResponseObject.getData());
            } catch (HttpException e) {
                System.out.println(e.getError());
            } catch (HttpBadRequestException e) {
                System.out.println(e.getErrors());
            }
        }
    }
}
