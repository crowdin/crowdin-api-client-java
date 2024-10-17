package com.crowdin.client.ai.model;

import java.util.Date;
import lombok.Data;

@Data
public class FineTuningDatasetDownload {
    private String url;
    private Date expireIn;
}