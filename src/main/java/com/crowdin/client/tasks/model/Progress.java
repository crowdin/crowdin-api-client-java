package com.crowdin.client.tasks.model;

import lombok.Data;

@Data
public class Progress {

    private Integer total;
    private Integer done;
    private Integer percent;
}
