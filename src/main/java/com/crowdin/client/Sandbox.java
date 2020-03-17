package com.crowdin.client;

import com.crowdin.client.core.http.exceptions.HttpBadRequestException;
import com.crowdin.client.core.http.exceptions.HttpException;
import com.crowdin.client.core.model.Credentials;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.storage.model.Storage;
import lombok.var;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sandbox {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            try {
                Credentials credentials = new Credentials(args[0], "oliynyk");
                var client = new Client(credentials);
                client.getStorageApi().addStorage("qwe.txt", new FileInputStream(new File("D:\\Docs\\file.txt")));
                ResponseList<Storage> storageResponseList = client.getStorageApi().listStorages(null, null);
                storageResponseList.getData().forEach(e -> System.out.println(e.getData().getId() + " : " + e.getData().getFileName()));
            } catch (HttpException e) {
                System.out.println(e.getError());
            } catch (HttpBadRequestException e) {
                System.out.println(e.getErrors());
            }
        }
    }
}
