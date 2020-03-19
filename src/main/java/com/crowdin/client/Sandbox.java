package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.DownloadLink;
import com.crowdin.client.core.model.ResponseObject;
import lombok.var;

import java.io.FileNotFoundException;

public class Sandbox {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            try {
                Credentials credentials = new Credentials(args[0], "oliynyk");
                var client = new Client(credentials);
                ResponseObject<DownloadLink> downloadLinkResponseObject = client.getSourceFilesApi().downloadFile(121, 29351);
                System.out.println(downloadLinkResponseObject.getData());
            } catch (HttpException e) {
                System.out.println(e.getError());
            } catch (HttpBadRequestException e) {
                System.out.println(e.getErrors());
            }
        }
    }
}
