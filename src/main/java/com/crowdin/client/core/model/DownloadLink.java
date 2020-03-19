package com.crowdin.client.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class DownloadLink {

    private String url;
    private Date expireIn;
}
