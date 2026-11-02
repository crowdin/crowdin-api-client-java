package com.crowdin.client.tasks.model;

import lombok.Data;

import java.util.Date;

@Data
public class TaskCost {

    private Double cost;
    /**
     * In UTC
     */
    private Date date;
    private String currency;
}
