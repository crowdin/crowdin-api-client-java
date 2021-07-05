package com.crowdin.client.tasks.model;

import lombok.Data;

@Data
public class TranslateProgress {

    private Integer total;
    private Integer done;
    private Integer percent;
}
